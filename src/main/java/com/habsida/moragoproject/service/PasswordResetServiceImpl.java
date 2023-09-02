package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.PasswordReset;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    private PasswordResetRepository passwordResetRepository;

    public PasswordResetServiceImpl (PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    @Override
    public List<PasswordReset> getAll () {
        return passwordResetRepository.findAll();
    }

    @Override
    public PasswordReset getById (Long id) {
        return passwordResetRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("PasswordReset is not found for the id - " + id);
        });
    }

    @Override
    public PasswordReset create (PasswordReset passwordReset) {
        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public PasswordReset update (PasswordReset passwordReset) {
        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public void deleteById (Long id) {
        passwordResetRepository.deleteById(id);
    }
}
