package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.TokenRefreshException;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.AuthenticationInput;
import com.habsida.moragoproject.model.input.UserCreateInput;
import com.habsida.moragoproject.model.input.RefreshTokenInput;
import com.habsida.moragoproject.model.input.RegistrationTranslatorInput;
import com.habsida.moragoproject.model.payload.AuthorizationPayload;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;
import com.habsida.moragoproject.security.JwtGenerator;
import com.habsida.moragoproject.security.RefreshTokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
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
    public AuthorizationPayload registrationUser(AuthenticationInput authenticationInput) {
        UserCreateInput userCreateInput = new UserCreateInput();

        if (userService.isExistsByPhone(authenticationInput.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + authenticationInput.getPhone());
        }
        if (authenticationInput.getPhone() == null || authenticationInput.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null");
        } else {
            userCreateInput.setPhone(authenticationInput.getPhone());
        }
        if (authenticationInput.getPassword() == null || authenticationInput.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            userCreateInput.setPassword(authenticationInput.getPassword());
        }

        userCreateInput.setBalance(0.0f);
        userCreateInput.setFirstName("");
        userCreateInput.setIsActive(true);
        userCreateInput.setIsDebtor(false);
        userCreateInput.setLastName("");
        userCreateInput.setOnBoardingStatus(0);
        userCreateInput.setRatings(0.0);
        userCreateInput.setTotalRatings(0);
        userCreateInput.setRoles(Arrays.asList(new Role(ERole.ROLE_USER)));

        User newUser = userService.create(userCreateInput);

        String jwtToken = jwtGenerator.generateTokenFromUsername(newUser.getPhone());
        com.habsida.moragoproject.model.entity.RefreshToken refreshToken = refreshTokenGenerator.createRefreshToken(newUser.getId());

        return new AuthorizationPayload(jwtToken, refreshToken.getToken());
    }

    @Override
    public AuthorizationPayload registrationTranslator(RegistrationTranslatorInput registrationTranslatorInput) {

        UserCreateInput userCreateInput = new UserCreateInput();

        if (userService.isExistsByPhone(registrationTranslatorInput.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + registrationTranslatorInput.getPhone());
        }
        if (registrationTranslatorInput.getPhone() == null || registrationTranslatorInput.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null");
        } else {
            userCreateInput.setPhone(registrationTranslatorInput.getPhone());
        }
        if (registrationTranslatorInput.getPassword() == null || registrationTranslatorInput.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            userCreateInput.setPassword(registrationTranslatorInput.getPassword());
        }

        userCreateInput.setBalance(0.0f);
        userCreateInput.setFirstName(registrationTranslatorInput.getFirstName());
        userCreateInput.setIsActive(false);
        userCreateInput.setIsDebtor(false);
        userCreateInput.setLastName(registrationTranslatorInput.getLastName());
        userCreateInput.setOnBoardingStatus(0);
        userCreateInput.setRatings(0.0);
        userCreateInput.setTotalRatings(0);
        userCreateInput.setRoles(Arrays.asList(new Role(ERole.ROLE_TRANSLATOR)));

        TranslatorProfile translatorProfile = registrationTranslatorInput.getTranslatorProfile();
        translatorProfile.setIsOnline(false);
        translatorProfile.setIsAvailable(false);

        //createUserInput.setTranslatorProfile(translatorProfile);

        User newUser = userService.create(userCreateInput);
        translatorProfile.setUser(newUser);

        translatorProfileRepository.save(translatorProfile);

        String jwtToken = jwtGenerator.generateTokenFromUsername(newUser.getPhone());
        com.habsida.moragoproject.model.entity.RefreshToken refreshToken = refreshTokenGenerator.createRefreshToken(newUser.getId());

        return new AuthorizationPayload(jwtToken, refreshToken.getToken());
    }

    @Override
    public AuthorizationPayload registrationAdmin(AuthenticationInput authenticationInput) {
        UserCreateInput userCreateInput = new UserCreateInput();

        if (userService.isExistsByPhone(authenticationInput.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + authenticationInput.getPhone());
        }
        if (authenticationInput.getPhone() == null || authenticationInput.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null");
        } else {
            userCreateInput.setPhone(authenticationInput.getPhone());
        }
        if (authenticationInput.getPassword() == null || authenticationInput.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            userCreateInput.setPassword(authenticationInput.getPassword());
        }

        userCreateInput.setBalance(0.0f);
        userCreateInput.setFirstName("");
        userCreateInput.setIsActive(true);
        userCreateInput.setIsDebtor(false);
        userCreateInput.setLastName("");
        userCreateInput.setOnBoardingStatus(0);
        userCreateInput.setRatings(0.0);
        userCreateInput.setTotalRatings(0);
        userCreateInput.setRoles(Arrays.asList(new Role(ERole.ROLE_USER),
                new Role(ERole.ROLE_TRANSLATOR),
                new Role(ERole.ROLE_ADMIN)));

        User newUser = userService.create(userCreateInput);

        String jwtToken = jwtGenerator.generateTokenFromUsername(newUser.getPhone());
        com.habsida.moragoproject.model.entity.RefreshToken refreshToken = refreshTokenGenerator.createRefreshToken(newUser.getId());

        return new AuthorizationPayload(jwtToken, refreshToken.getToken());
    }

    @Override
    public AuthorizationPayload loginUser(AuthenticationInput authenticationInput) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationInput.getPhone(),
                        authenticationInput.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtGenerator.generateJwtToken(userDetails);

        com.habsida.moragoproject.model.entity.RefreshToken refreshToken = refreshTokenGenerator.createRefreshToken(userService.getByPhone(userDetails.getUsername()).getId());
        return new AuthorizationPayload(
                jwtToken,
                refreshToken.getToken());
    }

    @Override
    public AuthorizationPayload refreshToken(RefreshTokenInput refreshTokenInput) {
        String refreshToken = refreshTokenInput.getRefreshToken();

        return refreshTokenGenerator.findByToken(refreshToken)
                .map(refreshTokenGenerator::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String jwtToken = jwtGenerator.generateTokenFromUsername(user.getPhone());
                    return new AuthorizationPayload(jwtToken, refreshToken);
                        })
                .orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
    }
}
