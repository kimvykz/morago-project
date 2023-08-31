package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Deposit;

import java.util.List;

public interface DepositService {
    List<Deposit> getAllItems();
    Deposit getItemById(Long id);
    Deposit createItem(Deposit deposit);
    Deposit modifyItem(Deposit deposit);
    void removeItem(Long id);
}
