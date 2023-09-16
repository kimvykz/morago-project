package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.payload.request.*;
import com.habsida.moragoproject.model.payload.response.LoginPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RegistrationPayloadResponse;
import com.habsida.moragoproject.model.payload.response.RefreshTokenResponse;

public interface AuthService {
    RegistrationPayloadResponse registrationUser(RegistrationUserRequest registrationUserRequest);
    RegistrationPayloadResponse registrationTranslator(RegistrationTranslatorRequest registrationTranslatorRequest);
    RegistrationPayloadResponse registrationAdmin(RegistrationAdminRequest registrationAdminRequest);
    LoginPayloadResponse loginUser(LoginRequest loginRequest);
    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
