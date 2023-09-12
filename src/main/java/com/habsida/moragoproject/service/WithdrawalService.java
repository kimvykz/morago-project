package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.input.CreateWithdrawalInput;
import com.habsida.moragoproject.model.input.UpdateWithdrawalInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface WithdrawalService {
    List<Withdrawal> getAll ();
    Page<Withdrawal> getAllPaged (PageRequest pageRequest);
    Withdrawal getById (Long id);
    Withdrawal create (CreateWithdrawalInput createWithdrawalInput);
    Withdrawal update (UpdateWithdrawalInput updateWithdrawalInput);
    Boolean deleteById(Long id);
}
