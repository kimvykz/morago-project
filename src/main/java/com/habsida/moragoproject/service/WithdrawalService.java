package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Withdrawal;

import java.util.List;

public interface WithdrawalService {
    List<Withdrawal> getAllItems();
    Withdrawal getItemById(Long id);
    Withdrawal createItem(Withdrawal withdrawal);
    Withdrawal modifyItem(Withdrawal withdrawal);
    void removeItem(Long id);
}
