package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.LoginUserInput;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.RegistrationUserInput;
import com.habsida.moragoproject.model.payload.LoginPayload;
import com.habsida.moragoproject.service.AuthService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @QueryMapping("login")
    public LoginPayload login(@Argument(name = "loginUserInput") LoginUserInput loginUserInput) {

        return authService.loginUser(loginUserInput);
    }

    @MutationMapping(name = "registration")
    public User registration (@Valid @Argument(name = "registrationUserInput") RegistrationUserInput registrationUserInput) {

        return authService.registerUser(registrationUserInput);
    }
}
