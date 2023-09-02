package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Withdrawal;

import java.util.List;

public interface WithdrawalService {
    List<Withdrawal> getAll ();
    Withdrawal getById (Long id);
    Withdrawal create (Withdrawal withdrawal);
    Withdrawal update (Withdrawal withdrawal);
    void deleteById(Long id);
}
