package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordResetCreateInput;
import com.habsida.moragoproject.model.input.PasswordResetUpdateInput;
import com.habsida.moragoproject.model.input.ResetCodeHashInput;
import com.habsida.moragoproject.model.payload.PasswordResetPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PasswordResetService {
    List<PasswordReset> getAll ();
    Page<PasswordReset> getAllByPaging (PageRequest pageRequest);
    PasswordReset getById (Long id);
    PasswordReset create (PasswordResetCreateInput passwordResetCreateInput);
    PasswordReset update (PasswordResetUpdateInput passwordResetUpdateInput);
    Boolean deleteById (Long id);
    PasswordResetPayload requestPasswordReset(String phone);
    Boolean checkResetCodeHash(ResetCodeHashInput resetCodeHashInput);

}
