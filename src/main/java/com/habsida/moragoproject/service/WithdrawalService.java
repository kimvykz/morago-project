package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.input.WithdrawalCreateInput;
import com.habsida.moragoproject.model.input.WithdrawalUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface WithdrawalService {
    List<Withdrawal> getAll ();
    Page<Withdrawal> getAllByPaging (PageRequest pageRequest);
    Withdrawal getById (Long id);
    Withdrawal create (WithdrawalCreateInput withdrawalCreateInput);
    Withdrawal update (WithdrawalUpdateInput withdrawalUpdateInput);
    Boolean deleteById(Long id);
}
