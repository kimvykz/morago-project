package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.PasswordReset;

import java.util.List;

public interface PasswordResetService {
    List<PasswordReset> getAllItems();
    PasswordReset getItemById(Long id);
    PasswordReset createItem(PasswordReset passwordReset);
    PasswordReset modifyItem(PasswordReset passwordReset);
    void removeItem(Long id);
}
