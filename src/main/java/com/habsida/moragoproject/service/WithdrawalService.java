package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Withdrawal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface WithdrawalService {
    List<Withdrawal> getAll ();
    Page<Withdrawal> getAllPaged (PageRequest pageRequest);
    Withdrawal getById (Long id);
    Withdrawal create (Withdrawal withdrawal);
    Withdrawal update (Withdrawal withdrawal);
    Boolean deleteById(Long id);
}
