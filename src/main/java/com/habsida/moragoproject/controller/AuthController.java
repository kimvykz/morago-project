package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.LoginUserInput;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.RegistrationUserInput;
import com.habsida.moragoproject.model.payload.LoginPayload;
import com.habsida.moragoproject.security.JwtGenerator;
import com.habsida.moragoproject.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtGenerator jwtGenerator;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @QueryMapping("login")
    public LoginPayload login(@Argument(name = "loginUserInput") LoginUserInput loginUserInput) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserInput.getPhone(),
                        loginUserInput.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new LoginPayload(userService.getByPhone(loginUserInput.getPhone()), token);
    }

    @MutationMapping(name = "registration")
    public User registration (@Valid @Argument(name = "registrationUserInput") RegistrationUserInput registrationUserInput) {

        return userService.signUpUser(registrationUserInput);
    }
}
