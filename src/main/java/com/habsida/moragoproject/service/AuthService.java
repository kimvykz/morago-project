package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.LoginUserInput;
import com.habsida.moragoproject.model.input.RegistrationUserInput;
import com.habsida.moragoproject.model.payload.LoginPayload;

public interface AuthService {
    User registerUser(RegistrationUserInput registrationUserInput);
    LoginPayload loginUser(LoginUserInput loginUserInput);
}
