package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.PasswordReset;

import java.util.List;

public interface PasswordResetService {
    List<PasswordReset> getAll();
    PasswordReset getById(Long id);
    PasswordReset create(PasswordReset passwordReset);
    PasswordReset update(PasswordReset passwordReset);
    void deleteById(Long id);
}
