package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.model.input.CreateDepositInput;
import com.habsida.moragoproject.model.input.UpdateDepositInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DepositService {
    List<Deposit> getAll();
    Page<Deposit> getAllPaged(PageRequest pageRequest);
    Deposit getById(Long id);
    Deposit create(CreateDepositInput createDepositInput);
    Deposit update(UpdateDepositInput updateDepositInput);
    Boolean deleteById(Long id);
}
