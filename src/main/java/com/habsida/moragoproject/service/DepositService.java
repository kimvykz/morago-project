package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.model.input.DepositCreateInput;
import com.habsida.moragoproject.model.input.DepositUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DepositService {
    List<Deposit> getAll ();
    Page<Deposit> getAllByPaging (PageRequest pageRequest);
    Deposit getById (Long id);
    Deposit create (DepositCreateInput depositCreateInput);
    Deposit update (DepositUpdateInput depositUpdateInput);
    Boolean deleteById (Long id);
}
