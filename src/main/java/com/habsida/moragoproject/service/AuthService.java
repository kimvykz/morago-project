package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.payload.request.LoginUserRequest;
import com.habsida.moragoproject.model.payload.request.RegistrationUserRequest;
import com.habsida.moragoproject.model.payload.request.RefreshTokenRequest;
import com.habsida.moragoproject.model.payload.response.LoginPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RegistrationPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RefreshTokenResponse;

public interface AuthService {
    RegistrationPayloadResponse registerUser(RegistrationUserRequest registrationUserRequest);
    LoginPayloadResponse loginUser(LoginUserRequest loginUserRequest);

    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
