package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateWithdrawalInput;
import com.habsida.moragoproject.model.input.UpdateWithdrawalInput;
import com.habsida.moragoproject.model.entity.Withdrawal;
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
public class WithdrawalController {

    private WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @QueryMapping(name = "getWithdrawals")
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
    public Withdrawal create (@Valid @Argument(name = "withdrawalInput") CreateWithdrawalInput createWithdrawalInput) {
        return withdrawalService.create(createWithdrawalInput);
    }

    @MutationMapping(name = "updateWithdrawal")
    public Withdrawal update (@Valid @Argument(name = "withdrawalInput") UpdateWithdrawalInput updateWithdrawalInput) {
        return withdrawalService.update(updateWithdrawalInput);
    }

    @MutationMapping(name = "deleteWithdrawalById")
    public Boolean deleteById (@Argument Long id) {
        return withdrawalService.deleteById(id);
    }


}
