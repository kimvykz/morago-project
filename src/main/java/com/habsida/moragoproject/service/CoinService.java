package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Coin;

import java.util.List;

public interface CoinService {
    List<Coin> getAllItems();
    Coin getItemById(Long id);
    Coin createItem(Coin coin);
    Coin modifyItem(Coin coin);
    void removeItem(Long id);
}
