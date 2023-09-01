package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.PasswordReset;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService{
    private PasswordResetRepository passwordResetRepository;

    public PasswordResetServiceImpl(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    @Override
    public List<PasswordReset> getAllItems() {
        return passwordResetRepository.findAll();
    }

    @Override
    public PasswordReset getItemById(Long id) {
        return passwordResetRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("PasswordReset is not found for the id - " + id);
        });
    }

    @Override
    public PasswordReset createItem(PasswordReset passwordReset) {
        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public PasswordReset modifyItem(PasswordReset passwordReset) {
        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public void removeItem(Long id) {
        passwordResetRepository.deleteById(id);
    }
}
