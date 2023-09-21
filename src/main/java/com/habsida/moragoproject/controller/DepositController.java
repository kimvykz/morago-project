package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.DepositCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.DepositUpdateInput;
import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.service.DepositService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class DepositController {

    private DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @QueryMapping(name = "getDeposits")
    public Iterable<Deposit> getAll (){
        return depositService.getAll();
    }

    @QueryMapping(name = "getDepositById")
    public Deposit getById (@Argument Long id) {
        return depositService.getById(id);
    }

    @QueryMapping(name = "getDepositsByPaging")
    public Page<Deposit> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return depositService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createDeposit")
    public Deposit create (@Valid @Argument(name = "depositInput") DepositCreateInput depositCreateInput) {

        return depositService.create(depositCreateInput);
    }

    @MutationMapping(name = "updateDeposit")
    public Deposit update (@Valid @Argument(name = "depositInput") DepositUpdateInput depositUpdateInput) {

        return depositService.update(depositUpdateInput);
    }

    @MutationMapping(name = "deleteDepositById")
    public Boolean deleteById (@Argument Long id) {
        return depositService.deleteById(id);
    }


}
