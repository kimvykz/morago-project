package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.model.input.CreateCoinInput;
import com.habsida.moragoproject.model.input.UpdateCoinInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CoinService {
    List<Coin> getAll ();
    Page<Coin> getAllByPaging (PageRequest pageRequest);
    Coin getById (Long id);
    Coin create (CreateCoinInput createCoinInput);
    Coin update (UpdateCoinInput updateCoinInput);
    Boolean deleteById (Long id);
}
