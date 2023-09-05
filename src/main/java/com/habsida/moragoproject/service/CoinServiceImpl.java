package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.repository.CoinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinServiceImpl implements CoinService{

    private CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public List<Coin> getAll() {
        return coinRepository.findAll();
    }

    @Override
    public Page<Coin> getAllPaged(PageRequest pageRequest) {
        return coinRepository.findAll(pageRequest);
    }

    @Override
    public Coin getById(Long id) {
        return coinRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Coin is not found for the id - " + id);
        });
    }

    @Override
    public Coin create(Coin coin) {
        return coinRepository.save(coin);
    }

    @Override
    public Coin update(Coin coin) {
        return coinRepository.save(coin);
    }

    @Override
    public Boolean deleteById(Long id) {
        coinRepository.deleteById(id);
        return true;
    }
}
