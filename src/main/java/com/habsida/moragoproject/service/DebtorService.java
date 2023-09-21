package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.model.input.DebtorCreateInput;
import com.habsida.moragoproject.model.input.DebtorUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DebtorService {
    List<Debtor> getAll ();
    Page<Debtor> getAllByPaging (PageRequest pageRequest);
    Debtor getById (Long id);
    Debtor create (DebtorCreateInput debtorCreateInput);
    Debtor update (DebtorUpdateInput debtorUpdateInput);
    Boolean deleteById (Long id);
}
