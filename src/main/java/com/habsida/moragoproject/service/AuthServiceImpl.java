package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.TokenRefreshException;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateUserInput;
import com.habsida.moragoproject.model.payload.request.LoginUserRequest;
import com.habsida.moragoproject.model.payload.request.RegistrationUserRequest;
import com.habsida.moragoproject.model.payload.request.RefreshTokenRequest;
import com.habsida.moragoproject.model.payload.response.LoginPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RegistrationPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RefreshTokenResponse;
import com.habsida.moragoproject.security.JwtGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtGenerator jwtGenerator;
    private RefreshTokenService refreshTokenService;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserService userService,
                           JwtGenerator jwtGenerator,
                           RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public RegistrationPayloadResponse registerUser(RegistrationUserRequest registrationUserRequest) {
        CreateUserInput createUserInput = new CreateUserInput();

        if (userService.isExistsByPhone(registrationUserRequest.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + registrationUserRequest.getPhone());
        }
        if (registrationUserRequest.getPhone() == null || registrationUserRequest.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null");
        } else {
            createUserInput.setPhone(registrationUserRequest.getPhone());
        }
        if (registrationUserRequest.getPassword() == null || registrationUserRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            createUserInput.setPassword(registrationUserRequest.getPassword());
        }

        createUserInput.setBalance(0.0f);
        createUserInput.setFirstName("");
        createUserInput.setIsActive(true);
        createUserInput.setIsDebtor(false);
        createUserInput.setLastName("");
        createUserInput.setOnBoardingStatus(0);
        createUserInput.setRatings(0.0);
        createUserInput.setTotalRatings(0);
        createUserInput.setRoles(registrationUserRequest.getRoles());

        User newUser = userService.create(createUserInput);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(newUser.getId());

        return new RegistrationPayloadResponse(newUser, refreshToken.getToken()) ;
    }

    @Override
    public LoginPayloadResponse loginUser(LoginUserRequest loginUserRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequest.getPhone(),
                        loginUserRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtGenerator.generateJwtToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userService.getByPhone(userDetails.getUsername()).getId());
        return new LoginPayloadResponse(userService.getByPhone(loginUserRequest.getPhone()),
                jwtToken,
                refreshToken.getToken());
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtGenerator.generateTokenFromUsername(user.getPhone());
                    return new RefreshTokenResponse(token, refreshToken);
                        })
                .orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
    }
}
