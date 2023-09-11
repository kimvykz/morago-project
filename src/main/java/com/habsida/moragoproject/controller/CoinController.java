package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateCoinInput;
import com.habsida.moragoproject.model.input.UpdateCoinInput;
import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.service.CoinService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class CoinController {
    private ModelMapper modelMapper;

    private CoinService coinService;

    public CoinController(ModelMapper modelMapper,
                          CoinService coinService) {
        this.modelMapper = modelMapper;
        this.coinService = coinService;
    }

    @QueryMapping(name = "getCoins")
    public Iterable<Coin> getAll (){
        return coinService.getAll();
    }

    @QueryMapping(name = "getCoinById")
    public Coin getById (@Argument Long id) {
        return coinService.getById(id);
    }

    @QueryMapping(name = "getCoinsPaged")
    public Page<Coin> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return coinService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createCoin")
    public Coin create (@Valid @Argument(name = "coin") CreateCoinInput createCoinInput) {
        Coin coin = modelMapper.map(createCoinInput, Coin.class);
        return coinService.create(coin);
    }

    @MutationMapping(name = "updateCoinById")
    public Coin updateById (@Valid @Argument Long id,
                              @Argument(name = "coin") UpdateCoinInput updateCoinInput) {
        Coin coin = coinService.getById(id);
        modelMapper.map(updateCoinInput, coin);
        return coinService.update(coin);
    }

    @MutationMapping(name = "deleteCoinById")
    public Boolean deleteById (@Argument Long id) {
        return coinService.deleteById(id);
    }


}
