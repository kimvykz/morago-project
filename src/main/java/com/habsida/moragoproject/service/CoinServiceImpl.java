package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Coin;
import com.habsida.moragoproject.repository.CoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinServiceImpl implements CoinService{

    private CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public List<Coin> getAllItems() {
        return coinRepository.findAll();
    }

    @Override
    public Coin getItemById(Long id) {
        return coinRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Coin is not found for the id - " + id);
        });
    }

    @Override
    public Coin createItem(Coin coin) {
        return coinRepository.save(coin);
    }

    @Override
    public Coin modifyItem(Coin coin) {
        return coinRepository.save(coin);
    }

    @Override
    public void removeItem(Long id) {
        coinRepository.deleteById(id);
    }
}
