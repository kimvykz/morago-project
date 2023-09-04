package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.PasswordReset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PasswordResetService {
    List<PasswordReset> getAll();
    Page<PasswordReset> getAllPaged(PageRequest pageRequest);
    PasswordReset getById(Long id);
    PasswordReset create(PasswordReset passwordReset);
    PasswordReset update(PasswordReset passwordReset);
    Boolean deleteById(Long id);
}
