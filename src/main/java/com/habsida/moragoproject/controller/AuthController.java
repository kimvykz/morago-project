package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.payload.request.LoginUserRequest;
import com.habsida.moragoproject.model.payload.request.RegistrationUserRequest;
import com.habsida.moragoproject.model.payload.request.RefreshTokenRequest;
import com.habsida.moragoproject.model.payload.response.LoginPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RegistrationPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RefreshTokenResponse;
import com.habsida.moragoproject.service.AuthService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @QueryMapping("login")
    public LoginPayloadResponse login(@Argument(name = "loginUserInput") LoginUserRequest loginUserRequest) {

        return authService.loginUser(loginUserRequest);
    }

    @MutationMapping(name = "registration")
    public RegistrationPayloadResponse registration (@Valid @Argument(name = "registrationUserInput") RegistrationUserRequest registrationUserRequest) {

        return authService.registerUser(registrationUserRequest);
    }

    @QueryMapping("refreshToken")
    public RefreshTokenResponse refreshToken(@Argument(name = "refreshTokenInput") RefreshTokenRequest refreshTokenRequest) {

        return authService.refreshToken(refreshTokenRequest);
    }

}
