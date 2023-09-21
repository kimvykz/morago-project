package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.WithdrawalCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.WithdrawalUpdateInput;
import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.service.WithdrawalService;
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

    public WithdrawalController (WithdrawalService withdrawalService) {
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

    @QueryMapping(name = "getWithdrawalsByPaging")
    public Page<Withdrawal> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return withdrawalService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createWithdrawal")
    public Withdrawal create (@Valid @Argument(name = "withdrawalInput") WithdrawalCreateInput withdrawalCreateInput) {
        return withdrawalService.create(withdrawalCreateInput);
    }

    @MutationMapping(name = "updateWithdrawal")
    public Withdrawal update (@Valid @Argument(name = "withdrawalInput") WithdrawalUpdateInput withdrawalUpdateInput) {
        return withdrawalService.update(withdrawalUpdateInput);
    }

    @MutationMapping(name = "deleteWithdrawalById")
    public Boolean deleteById (@Argument Long id) {
        return withdrawalService.deleteById(id);
    }


}
