package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateCoinInput;
import com.habsida.moragoproject.model.input.PaginationInput;
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


    private CoinService coinService;

    public CoinController(CoinService coinService) {
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

    @QueryMapping(name = "getCoinsByPaging")
    public Page<Coin> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return coinService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createCoin")
    public Coin create (@Valid @Argument(name = "coinInput") CreateCoinInput createCoinInput) {

        return coinService.create(createCoinInput);
    }

    @MutationMapping(name = "updateCoin")
    public Coin update (@Valid @Argument(name = "coinInput") UpdateCoinInput updateCoinInput) {

        return coinService.update(updateCoinInput);
    }

    @MutationMapping(name = "deleteCoinById")
    public Boolean deleteById (@Argument Long id) {
        return coinService.deleteById(id);
    }


}
