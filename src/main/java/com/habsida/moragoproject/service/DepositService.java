package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Deposit;

import java.util.List;

public interface DepositService {
    List<Deposit> getAll();
    Deposit getById(Long id);
    Deposit create(Deposit deposit);
    Deposit update(Deposit deposit);
    void deleteById(Long id);
}
