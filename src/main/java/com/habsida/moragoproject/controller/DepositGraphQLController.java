package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.DepositDTO;
import com.habsida.moragoproject.entity.Deposit;
import com.habsida.moragoproject.service.DepositService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class DepositGraphQLController {
    private ModelMapper modelMapper;

    private DepositService depositService;

    public DepositGraphQLController(ModelMapper modelMapper,
                                    DepositService depositService) {
        this.modelMapper = modelMapper;
        this.depositService = depositService;
    }

    @QueryMapping(name = "getDeposit")
    public Iterable<Deposit> getAll (){
        return depositService.getAll();
    }

    @QueryMapping(name = "getDepositById")
    public Deposit getById (@Argument Long id) {
        return depositService.getById(id);
    }

    @QueryMapping(name = "getDepositsPaged")
    public Page<Deposit> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return depositService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createDeposit")
    public Deposit create (@Valid @Argument(name = "deposit") DepositDTO depositDTO) {
        Deposit deposit = modelMapper.map(depositDTO, Deposit.class);
        return depositService.create(deposit);
    }

    @MutationMapping(name = "updateDepositById")
    public Deposit updateById (@Valid @Argument Long id,
                              @Argument(name = "deposit") DepositDTO depositDTO) {
        Deposit deposit = depositService.getById(id);
        modelMapper.map(depositDTO, deposit);
        return depositService.update(deposit);
    }

    @MutationMapping(name = "deleteDepositById")
    public Boolean deleteById (@Argument Long id) {
        return depositService.deleteById(id);
    }


}
