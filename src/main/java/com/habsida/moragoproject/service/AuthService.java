package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.input.AuthenticationInput;
import com.habsida.moragoproject.model.input.RefreshTokenInput;
import com.habsida.moragoproject.model.input.RegistrationTranslatorInput;
import com.habsida.moragoproject.model.payload.AuthorizationPayload;

public interface AuthService {
    AuthorizationPayload registrationUser(AuthenticationInput authenticationInput);
    AuthorizationPayload registrationTranslator(RegistrationTranslatorInput registrationTranslatorInput);
    AuthorizationPayload registrationAdmin(AuthenticationInput authenticationInput);
    AuthorizationPayload loginUser(AuthenticationInput authenticationInput);
    AuthorizationPayload refreshToken(RefreshTokenInput refreshTokenInput);
}
