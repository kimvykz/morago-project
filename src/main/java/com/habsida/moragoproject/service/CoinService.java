package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Coin;

import java.util.List;

public interface CoinService {
    List<Coin> getAll();
    Coin getById(Long id);
    Coin create(Coin coin);
    Coin update(Coin coin);
    void deleteById(Long id);
}
