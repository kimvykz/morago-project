package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DepositService {
    List<Deposit> getAll();
    Page<Deposit> getAllPaged(PageRequest pageRequest);
    Deposit getById(Long id);
    Deposit create(Deposit deposit);
    Deposit update(Deposit deposit);
    Boolean deleteById(Long id);
}
