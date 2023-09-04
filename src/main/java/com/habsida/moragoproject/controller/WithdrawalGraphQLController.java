package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.WithdrawalDTO;
import com.habsida.moragoproject.entity.Withdrawal;
import com.habsida.moragoproject.service.WithdrawalService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class WithdrawalGraphQLController {
    private ModelMapper modelMapper;

    private WithdrawalService withdrawalService;

    public WithdrawalGraphQLController(ModelMapper modelMapper,
                                       WithdrawalService withdrawalService) {
        this.modelMapper = modelMapper;
        this.withdrawalService = withdrawalService;
    }

    @QueryMapping(name = "getWithdrawal")
    public Iterable<Withdrawal> getAll (){
        return withdrawalService.getAll();
    }

    @QueryMapping(name = "getWithdrawalById")
    public Withdrawal getById (@Argument Long id) {
        return withdrawalService.getById(id);
    }

    @QueryMapping(name = "getWithdrawalsPaged")
    public Page<Withdrawal> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return withdrawalService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createWithdrawal")
    public Withdrawal create (@Valid @Argument(name = "withdrawal") WithdrawalDTO withdrawalDTO) {
        Withdrawal withdrawal = modelMapper.map(withdrawalDTO, Withdrawal.class);
        return withdrawalService.create(withdrawal);
    }

    @MutationMapping(name = "updateWithdrawalById")
    public Withdrawal updateById (@Valid @Argument Long id,
                              @Argument(name = "withdrawal") WithdrawalDTO withdrawalDTO) {
        Withdrawal withdrawal = withdrawalService.getById(id);
        modelMapper.map(withdrawalDTO, withdrawal);
        return withdrawalService.update(withdrawal);
    }

    @MutationMapping(name = "deleteWithdrawalById")
    public Boolean deleteById (@Argument Long id) {
        return withdrawalService.deleteById(id);
    }


}
