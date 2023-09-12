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
    private ModelMapper modelMapper;

    public CoinServiceImpl(CoinRepository coinRepository, ModelMapper modelMapper) {
        this.coinRepository = coinRepository;
        this.modelMapper = modelMapper;
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
        Coin coin = modelMapper.map(createCoinInput, Coin.class);
        return coinRepository.save(coin);
    }

    @Override
    public Coin update(UpdateCoinInput updateCoinInput) {
        Coin coin = getById(updateCoinInput.getId());
        modelMapper.map(updateCoinInput, coin);
        return coinRepository.save(coin);
    }

    @Override
    public Boolean deleteById(Long id) {
        coinRepository.deleteById(id);
        return true;
    }
}
