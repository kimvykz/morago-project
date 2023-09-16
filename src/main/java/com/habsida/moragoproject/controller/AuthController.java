package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.payload.request.*;
import com.habsida.moragoproject.model.payload.response.LoginPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RegistrationPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RefreshTokenResponse;
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
    public LoginPayloadResponse login(@Argument(name = "loginUserInput") LoginRequest loginRequest) {

        return authService.loginUser(loginRequest);
    }

    @MutationMapping(name = "registrationUser")
    public RegistrationPayloadResponse registrationUser (@Valid @Argument(name = "registrationUserInput") RegistrationUserRequest registrationUserRequest) {

        return authService.registrationUser(registrationUserRequest);
    }

    @MutationMapping(name = "registrationTranslator")
    public RegistrationPayloadResponse registrationTranslator (@Valid @Argument(name = "registrationTranslatorInput") RegistrationTranslatorRequest registrationTranslatorRequest) {

        return authService.registrationTranslator(registrationTranslatorRequest);
    }

    @MutationMapping(name = "registrationAdmin")
    public RegistrationPayloadResponse registrationAdmin (@Valid @Argument(name = "registrationAdminInput") RegistrationAdminRequest registrationAdminRequest) {

        return authService.registrationAdmin(registrationAdminRequest);
    }

    @QueryMapping("refreshToken")
    public RefreshTokenResponse refreshToken(@Argument(name = "refreshTokenInput") RefreshTokenRequest refreshTokenRequest) {

        return authService.refreshToken(refreshTokenRequest);
    }

}
