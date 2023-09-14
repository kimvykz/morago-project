package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.model.input.CreateCoinInput;
import com.habsida.moragoproject.model.input.UpdateCoinInput;
import com.habsida.moragoproject.repository.CoinRepository;
import org.modelmapper.ModelMapper;
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
    public Coin create(CreateCoinInput createCoinInput) {
        Coin coin = new Coin();

        if (createCoinInput.getCoin() == null) {
            throw new IllegalArgumentException("field coin cannot be null");
        } else {
            coin.setCoin(createCoinInput.getCoin());
        }
        if (createCoinInput.getWon() == null) {
            throw new IllegalArgumentException("field won cannot be null");
        } else {
            coin.setWon(createCoinInput.getWon());
        }

        return coinRepository.save(coin);
    }

    @Override
    public Coin update(UpdateCoinInput updateCoinInput) {
        Coin coin = getById(updateCoinInput.getId());

        if (updateCoinInput.getCoin() != null
            && !coin.getCoin().equals(updateCoinInput.getCoin())) {
            coin.setCoin(updateCoinInput.getCoin());
        }
        if (updateCoinInput.getWon() != null
            && !coin.getWon().equals(updateCoinInput.getWon())) {
            coin.setWon(updateCoinInput.getWon());
        }

        return coinRepository.save(coin);
    }

    @Override
    public Boolean deleteById(Long id) {
        coinRepository.deleteById(id);
        return true;
    }
}
