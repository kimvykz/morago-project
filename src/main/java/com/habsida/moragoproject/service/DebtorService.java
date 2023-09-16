package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.model.input.CreateDebtorInput;
import com.habsida.moragoproject.model.input.UpdateDebtorInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DebtorService {
    List<Debtor> getAll ();
    Page<Debtor> getAllByPaging (PageRequest pageRequest);
    Debtor getById (Long id);
    Debtor create (CreateDebtorInput createDebtorInput);
    Debtor update (UpdateDebtorInput updateDebtorInput);
    Boolean deleteById (Long id);
}
