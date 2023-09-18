package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.TokenRefreshException;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.CreateUserInput;
import com.habsida.moragoproject.model.payload.request.*;
import com.habsida.moragoproject.model.payload.response.LoginPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RegistrationPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RefreshTokenResponse;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;
import com.habsida.moragoproject.security.JwtGenerator;
import com.habsida.moragoproject.security.RefreshTokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService{

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtGenerator jwtGenerator;
    private RefreshTokenGenerator refreshTokenGenerator;
    private TranslatorProfileRepository translatorProfileRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserService userService,
                           JwtGenerator jwtGenerator,
                           RefreshTokenGenerator refreshTokenGenerator,
                           TranslatorProfileRepository translatorProfileRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
        this.refreshTokenGenerator = refreshTokenGenerator;
        this.translatorProfileRepository = translatorProfileRepository;
    }

    @Override
    public RegistrationPayloadResponse registrationUser(RegistrationUserRequest registrationUserRequest) {
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
        createUserInput.setRoles(Arrays.asList(new Role(ERole.ROLE_USER)));

        User newUser = userService.create(createUserInput);

        String jwtToken = jwtGenerator.generateTokenFromUsername(newUser.getPhone());
        RefreshToken refreshToken = refreshTokenGenerator.createRefreshToken(newUser.getId());

        return new RegistrationPayloadResponse(jwtToken, refreshToken.getToken());
    }

    @Override
    public RegistrationPayloadResponse registrationTranslator(RegistrationTranslatorRequest registrationTranslatorRequest) {
        System.out.println(registrationTranslatorRequest.getTranslatorProfile().getThemes());

        CreateUserInput createUserInput = new CreateUserInput();

        if (userService.isExistsByPhone(registrationTranslatorRequest.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + registrationTranslatorRequest.getPhone());
        }
        if (registrationTranslatorRequest.getPhone() == null || registrationTranslatorRequest.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null");
        } else {
            createUserInput.setPhone(registrationTranslatorRequest.getPhone());
        }
        if (registrationTranslatorRequest.getPassword() == null || registrationTranslatorRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            createUserInput.setPassword(registrationTranslatorRequest.getPassword());
        }

        createUserInput.setBalance(0.0f);
        createUserInput.setFirstName("");
        createUserInput.setIsActive(false);
        createUserInput.setIsDebtor(false);
        createUserInput.setLastName("");
        createUserInput.setOnBoardingStatus(0);
        createUserInput.setRatings(0.0);
        createUserInput.setTotalRatings(0);
        createUserInput.setRoles(Arrays.asList(new Role(ERole.ROLE_TRANSLATOR)));

        TranslatorProfile translatorProfile = registrationTranslatorRequest.getTranslatorProfile();
        translatorProfile.setIsOnline(false);
        translatorProfile.setIsAvailable(false);

        //createUserInput.setTranslatorProfile(translatorProfile);

        User newUser = userService.create(createUserInput);
        translatorProfile.setUser(newUser);
        translatorProfile.getThemes().stream()
                        .flatMap(theme -> theme.getFiles().stream())
                                .forEach(file -> file.setUser(newUser));

        translatorProfileRepository.save(translatorProfile);

        String jwtToken = jwtGenerator.generateTokenFromUsername(newUser.getPhone());
        RefreshToken refreshToken = refreshTokenGenerator.createRefreshToken(newUser.getId());

        return new RegistrationPayloadResponse(jwtToken, refreshToken.getToken());
    }

    @Override
    public RegistrationPayloadResponse registrationAdmin(RegistrationAdminRequest registrationAdminRequest) {
        CreateUserInput createUserInput = new CreateUserInput();

        if (userService.isExistsByPhone(registrationAdminRequest.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + registrationAdminRequest.getPhone());
        }
        if (registrationAdminRequest.getPhone() == null || registrationAdminRequest.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null");
        } else {
            createUserInput.setPhone(registrationAdminRequest.getPhone());
        }
        if (registrationAdminRequest.getPassword() == null || registrationAdminRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            createUserInput.setPassword(registrationAdminRequest.getPassword());
        }

        createUserInput.setBalance(0.0f);
        createUserInput.setFirstName("");
        createUserInput.setIsActive(true);
        createUserInput.setIsDebtor(false);
        createUserInput.setLastName("");
        createUserInput.setOnBoardingStatus(0);
        createUserInput.setRatings(0.0);
        createUserInput.setTotalRatings(0);
        createUserInput.setRoles(Arrays.asList(new Role(ERole.ROLE_USER),
                new Role(ERole.ROLE_TRANSLATOR),
                new Role(ERole.ROLE_ADMIN)));

        User newUser = userService.create(createUserInput);

        String jwtToken = jwtGenerator.generateTokenFromUsername(newUser.getPhone());
        RefreshToken refreshToken = refreshTokenGenerator.createRefreshToken(newUser.getId());

        return new RegistrationPayloadResponse(jwtToken, refreshToken.getToken());
    }

    @Override
    public LoginPayloadResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPhone(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtGenerator.generateJwtToken(userDetails);

        RefreshToken refreshToken = refreshTokenGenerator.createRefreshToken(userService.getByPhone(userDetails.getUsername()).getId());
        return new LoginPayloadResponse(
                jwtToken,
                refreshToken.getToken());
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenGenerator.findByToken(refreshToken)
                .map(refreshTokenGenerator::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String jwtToken = jwtGenerator.generateTokenFromUsername(user.getPhone());
                    return new RefreshTokenResponse(jwtToken, refreshToken);
                        })
                .orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
    }
}
