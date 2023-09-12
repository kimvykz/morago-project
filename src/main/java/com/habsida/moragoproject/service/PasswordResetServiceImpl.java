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
    private ModelMapper modelMapper;

    public PasswordResetServiceImpl (PasswordResetRepository passwordResetRepository, ModelMapper modelMapper) {
        this.passwordResetRepository = passwordResetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PasswordReset> getAll () {
        return passwordResetRepository.findAll();
    }

    @Override
    public Page<PasswordReset> getAllPaged(PageRequest pageRequest) {
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
        PasswordReset passwordReset = modelMapper.map(createPasswordResetInput, PasswordReset.class);
        if (passwordReset.getPhone() == null) {
            throw new IllegalArgumentException("field phone cannot be null");
        }
        if (passwordReset.getResetCode() == null) {
            throw new IllegalArgumentException("field resetCode cannot be null");
        }
        if (passwordReset.getToken() == null) {
            throw new IllegalArgumentException("field token cannot be null");
        }

        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public PasswordReset update (UpdatePasswordResetInput updatePasswordResetInput) {
        PasswordReset passwordReset = getById(updatePasswordResetInput.getId());
        modelMapper.map(updatePasswordResetInput, passwordReset);
        if (passwordReset.getPhone() == null) {
            throw new IllegalArgumentException("field phone cannot be null");
        }
        if (passwordReset.getResetCode() == null) {
            throw new IllegalArgumentException("field resetCode cannot be null");
        }
        if (passwordReset.getToken() == null) {
            throw new IllegalArgumentException("field token cannot be null");
        }
        return passwordResetRepository.save(passwordReset);
    }

    @Override
    public Boolean deleteById (Long id) {
        passwordResetRepository.deleteById(id);
        return true;
    }
}
