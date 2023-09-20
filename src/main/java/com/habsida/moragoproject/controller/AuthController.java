package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.AuthenticationInput;
import com.habsida.moragoproject.model.input.RefreshTokenInput;
import com.habsida.moragoproject.model.input.RegistrationTranslatorInput;
import com.habsida.moragoproject.model.payload.AuthorizationPayload;
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
    public AuthorizationPayload login(@Argument(name = "loginUserInput") AuthenticationInput authenticationInput) {

        return authService.loginUser(authenticationInput);
    }

    @MutationMapping(name = "registrationUser")
    public AuthorizationPayload registrationUser (@Valid @Argument(name = "registrationUserInput") AuthenticationInput authenticationInput) {

        return authService.registrationUser(authenticationInput);
    }

    @MutationMapping(name = "registrationTranslator")
    public AuthorizationPayload registrationTranslator (@Valid @Argument(name = "registrationTranslatorInput") RegistrationTranslatorInput registrationTranslatorInput) {

        return authService.registrationTranslator(registrationTranslatorInput);
    }

    @MutationMapping(name = "registrationAdmin")
    public AuthorizationPayload registrationAdmin (@Valid @Argument(name = "registrationAdminInput") AuthenticationInput authenticationInput) {

        return authService.registrationAdmin(authenticationInput);
    }

    @QueryMapping("refreshToken")
    public AuthorizationPayload refreshToken(@Argument(name = "refreshTokenInput") RefreshTokenInput refreshTokenInput) {

        return authService.refreshToken(refreshTokenInput);
    }

}
