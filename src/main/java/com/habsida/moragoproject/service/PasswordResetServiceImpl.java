package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordResetCreateInput;
import com.habsida.moragoproject.model.input.PasswordResetUpdateInput;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<PasswordReset> getAllByPaging (PageRequest pageRequest) {
        return passwordResetRepository.findAll(pageRequest);
    }

    @Override
    public PasswordReset getById (Long id) {
        return passwordResetRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("PasswordReset is not found for the id - " + id);
        });
    }

    @Override
    public PasswordReset create (PasswordResetCreateInput passwordResetCreateInput) {
        PasswordReset passwordReset = new PasswordReset();

        if (passwordResetCreateInput.getPhone() == null
            || passwordResetCreateInput.getPhone().isBlank()) {
            throw new IllegalArgumentException("field phone cannot be null");
        } else {
            passwordReset.setPhone(passwordResetCreateInput.getPhone());
        }
        if (passwordResetCreateInput.getResetCode() == null) {
            throw new IllegalArgumentException("field resetCode cannot be null");
        } else {
            passwordReset.setResetCode(passwordResetCreateInput.getResetCode());
        }
        if (passwordResetCreateInput.getToken() == null
            || passwordResetCreateInput.getToken().isBlank()) {
            throw new IllegalArgumentException("field token cannot be null");
        } else {
            passwordReset.setToken(passwordResetCreateInput.getToken());
        }

        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public PasswordReset update (PasswordResetUpdateInput passwordResetUpdateInput) {
        PasswordReset passwordReset = getById(passwordResetUpdateInput.getId());

        if (passwordResetUpdateInput.getPhone() != null
            && !passwordResetUpdateInput.getPhone().isBlank()
            && !passwordReset.getPhone().equals(passwordResetUpdateInput.getPhone())) {
            passwordReset.setPhone(passwordResetUpdateInput.getPhone());
        }
        if (passwordResetUpdateInput.getResetCode() != null
            && !passwordReset.getResetCode().equals(passwordResetUpdateInput.getResetCode())) {
            passwordReset.setResetCode(passwordResetUpdateInput.getResetCode());
        }
        if (passwordResetUpdateInput.getToken() != null
            && !passwordReset.getToken().equals(passwordResetUpdateInput.getToken())
            && !passwordResetUpdateInput.getToken().isBlank()) {
            passwordReset.setToken(passwordResetUpdateInput.getToken());
        }
        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public Boolean deleteById (Long id) {
        passwordResetRepository.deleteById(id);
        return true;
    }
}
