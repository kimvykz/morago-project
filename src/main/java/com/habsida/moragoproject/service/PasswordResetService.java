package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordResetCreateInput;
import com.habsida.moragoproject.model.input.PasswordResetUpdateInput;
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
}
