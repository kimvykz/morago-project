package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Debtor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DebtorService {
    List<Debtor> getAll();
    Page<Debtor> getAllPaged(PageRequest pageRequest);
    Debtor getById(Long id);
    Debtor create(Debtor debtor);
    Debtor update(Debtor debtor);
    Boolean deleteById(Long id);
}
