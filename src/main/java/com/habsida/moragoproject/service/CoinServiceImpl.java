package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.model.input.CoinCreateInput;
import com.habsida.moragoproject.model.input.CoinUpdateInput;
import com.habsida.moragoproject.repository.CoinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinServiceImpl implements CoinService{

    private CoinRepository coinRepository;

    public CoinServiceImpl (CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public List<Coin> getAll () {
        return coinRepository.findAll();
    }

    @Override
    public Page<Coin> getAllByPaging (PageRequest pageRequest) {
        return coinRepository.findAll(pageRequest);
    }

    @Override
    public Coin getById (Long id) {
        return coinRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Coin is not found for the id - " + id);
        });
    }

    @Override
    public Coin create (CoinCreateInput coinCreateInput) {
        Coin coin = new Coin();

        if (coinCreateInput.getCoin() == null) {
            throw new IllegalArgumentException("field coin cannot be null");
        } else {
            coin.setCoin(coinCreateInput.getCoin());
        }
        if (coinCreateInput.getWon() == null) {
            throw new IllegalArgumentException("field won cannot be null");
        } else {
            coin.setWon(coinCreateInput.getWon());
        }

        return coinRepository.save(coin);
    }

    @Override
    public Coin update (CoinUpdateInput coinUpdateInput) {
        Coin coin = getById(coinUpdateInput.getId());

        if (coinUpdateInput.getCoin() != null
            && !coin.getCoin().equals(coinUpdateInput.getCoin())) {
            coin.setCoin(coinUpdateInput.getCoin());
        }
        if (coinUpdateInput.getWon() != null
            && !coin.getWon().equals(coinUpdateInput.getWon())) {
            coin.setWon(coinUpdateInput.getWon());
        }

        return coinRepository.save(coin);
    }

    @Override
    public Boolean deleteById (Long id) {
        coinRepository.deleteById(id);
        return true;
    }
}
