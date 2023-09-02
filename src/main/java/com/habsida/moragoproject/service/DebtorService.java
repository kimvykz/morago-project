package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Debtor;

import java.util.List;

public interface DebtorService {
    List<Debtor> getAll();
    Debtor getById(Long id);
    Debtor create(Debtor debtor);
    Debtor update(Debtor debtor);
    void deleteById(Long id);
}
