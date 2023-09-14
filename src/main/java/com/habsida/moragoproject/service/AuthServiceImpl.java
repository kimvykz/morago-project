package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateUserInput;
import com.habsida.moragoproject.model.input.LoginUserInput;
import com.habsida.moragoproject.model.input.RegistrationUserInput;
import com.habsida.moragoproject.model.payload.LoginPayload;
import com.habsida.moragoproject.security.JwtGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtGenerator jwtGenerator;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserService userService, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public User registerUser(RegistrationUserInput registrationUserInput) {
        CreateUserInput createUserInput = new CreateUserInput();

        if (userService.isExistsByPhone(registrationUserInput.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + registrationUserInput.getPhone());
        }
        if (registrationUserInput.getPhone() == null || registrationUserInput.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null");
        } else {
            createUserInput.setPhone(registrationUserInput.getPhone());
        }
        if (registrationUserInput.getPassword() == null || registrationUserInput.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            createUserInput.setPassword(registrationUserInput.getPassword());
        }

        createUserInput.setBalance(0.0f);
        createUserInput.setFirstName("");
        createUserInput.setIsActive(true);
        createUserInput.setIsDebtor(false);
        createUserInput.setLastName("");
        createUserInput.setOnBoardingStatus(0);
        createUserInput.setRatings(0.0);
        createUserInput.setTotalRatings(0);
        createUserInput.setRoles(registrationUserInput.getRoles());

        return userService.create(createUserInput);
    }

    @Override
    public LoginPayload loginUser(LoginUserInput loginUserInput) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserInput.getPhone(),
                        loginUserInput.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new LoginPayload(userService.getByPhone(loginUserInput.getPhone()), token);
    }
}
