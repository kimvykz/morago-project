package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Debtor;

import java.util.List;

public interface DebtorService {
    List<Debtor> getAllItems();
    Debtor getItemById(Long id);
    Debtor createItem(Debtor debtor);
    Debtor modifyItem(Debtor debtor);
    void removeItem(Long id);
}
