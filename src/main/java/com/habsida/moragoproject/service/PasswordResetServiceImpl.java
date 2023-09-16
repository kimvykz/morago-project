package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.CreatePasswordResetInput;
import com.habsida.moragoproject.model.input.UpdatePasswordResetInput;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import org.modelmapper.ModelMapper;
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
    public PasswordReset create (CreatePasswordResetInput createPasswordResetInput) {
        PasswordReset passwordReset = new PasswordReset();

        if (createPasswordResetInput.getPhone() == null
            || createPasswordResetInput.getPhone().isBlank()) {
            throw new IllegalArgumentException("field phone cannot be null");
        } else {
            passwordReset.setPhone(createPasswordResetInput.getPhone());
        }
        if (createPasswordResetInput.getResetCode() == null) {
            throw new IllegalArgumentException("field resetCode cannot be null");
        } else {
            passwordReset.setResetCode(createPasswordResetInput.getResetCode());
        }
        if (createPasswordResetInput.getToken() == null
            || createPasswordResetInput.getToken().isBlank()) {
            throw new IllegalArgumentException("field token cannot be null");
        } else {
            passwordReset.setToken(createPasswordResetInput.getToken());
        }

        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public PasswordReset update (UpdatePasswordResetInput updatePasswordResetInput) {
        PasswordReset passwordReset = getById(updatePasswordResetInput.getId());

        if (updatePasswordResetInput.getPhone() != null
            && !updatePasswordResetInput.getPhone().isBlank()
            && !passwordReset.getPhone().equals(updatePasswordResetInput.getPhone())) {
            passwordReset.setPhone(updatePasswordResetInput.getPhone());
        }
        if (updatePasswordResetInput.getResetCode() != null
            && !passwordReset.getResetCode().equals(updatePasswordResetInput.getResetCode())) {
            passwordReset.setResetCode(updatePasswordResetInput.getResetCode());
        }
        if (updatePasswordResetInput.getToken() != null
            && !passwordReset.getToken().equals(updatePasswordResetInput.getToken())
            && !updatePasswordResetInput.getToken().isBlank()) {
            passwordReset.setToken(updatePasswordResetInput.getToken());
        }
        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public Boolean deleteById (Long id) {
        passwordResetRepository.deleteById(id);
        return true;
    }
}
