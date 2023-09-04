package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Coin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CoinService {
    List<Coin> getAll();
    Page<Coin> getAllPaged(PageRequest pageRequest);
    Coin getById(Long id);
    Coin create(Coin coin);
    Coin update(Coin coin);
    Boolean deleteById(Long id);
}
