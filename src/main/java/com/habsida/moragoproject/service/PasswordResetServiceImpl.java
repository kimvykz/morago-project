package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordResetCreateInput;
import com.habsida.moragoproject.model.input.PasswordResetUpdateInput;
import com.habsida.moragoproject.model.input.ResetCodeHashInput;
import com.habsida.moragoproject.model.payload.PasswordResetPayload;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import com.habsida.moragoproject.repository.UserRepository;
import com.habsida.moragoproject.security.PasswordResetTokenGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    private PasswordResetRepository passwordResetRepository;
    private UserRepository userRepository;
    private PasswordResetTokenGenerator passwordResetTokenGenerator;

    public PasswordResetServiceImpl (PasswordResetRepository passwordResetRepository,
                                     UserRepository userRepository,
                                     PasswordResetTokenGenerator passwordResetTokenGenerator) {
        this.passwordResetRepository = passwordResetRepository;
        this.userRepository = userRepository;
        this.passwordResetTokenGenerator = passwordResetTokenGenerator;
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

    @Override
    public PasswordResetPayload requestPasswordReset(String phone) {
        if (!userRepository.existsByPhone(phone)) {
            throw new IllegalArgumentException("User with phone is not found - " + phone);
        }

        PasswordResetPayload passwordResetPayload = new PasswordResetPayload();
        PasswordReset passwordReset = new PasswordReset();
        Random random = new Random();
        //String token = passwordResetTokenGenerator.generateJwtPasswordResetToken();

        passwordReset.setResetCode(1000 + random.nextInt(9000));
        passwordReset.setPhone(phone);
        //passwordReset.setToken(token);
        passwordReset = passwordResetRepository.save(passwordReset);

        passwordResetPayload.setPasswordResetId(passwordReset.getId());
        passwordResetPayload.setExpirationTime(LocalDateTime.now().plusMinutes(10));

        //passwordResetPayload.setTimeCode(passwordResetTokenGenerator.getValidationDate(token).toInstant().toEpochMilli());

        passwordResetPayload.setHashcode((
                phone + passwordReset.getResetCode() + passwordResetPayload.getExpirationTime().toString()).hashCode());

        return passwordResetPayload;

    }

    @Override
    public String checkResetCodeHash(ResetCodeHashInput resetCodeHashInput) {

        PasswordReset passwordReset = passwordResetRepository.findById(
                resetCodeHashInput.getPasswordResetId()).orElseThrow(
                () -> new IllegalArgumentException("Password reset is not found by id - " +
                        resetCodeHashInput.getPasswordResetId())
        );

        if ((passwordReset.getPhone() + passwordReset.getResetCode()
                + resetCodeHashInput.getExpirationTime().toString()).hashCode()
                == resetCodeHashInput.getHashcode()) {
            String token = passwordResetTokenGenerator.generateJwtPasswordResetToken(passwordReset.getPhone());
            passwordReset.setToken(token);
            passwordResetRepository.save(passwordReset);
            return token;
        } else {
            throw new IllegalArgumentException("Hashcode does not match");
        }

    }



}
