package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.DebtorCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.DebtorUpdateInput;
import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.service.DebtorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class DebtorController {

    private DebtorService debtorService;

    public DebtorController(DebtorService debtorService) {
        this.debtorService = debtorService;
    }

    @QueryMapping(name = "getDebtors")
    public Iterable<Debtor> getAll (){
        return debtorService.getAll();
    }

    @QueryMapping(name = "getDebtorById")
    public Debtor getById (@Argument Long id) {
        return debtorService.getById(id);
    }

    @QueryMapping(name = "getDebtorsByPaging")
    public Page<Debtor> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return debtorService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createDebtor")
    public Debtor create (@Valid @Argument(name = "debtorInput") DebtorCreateInput debtorCreateInput) {

        return debtorService.create(debtorCreateInput);
    }

    @MutationMapping(name = "updateDebtor")
    public Debtor update (@Valid @Argument(name = "debtorInput") DebtorUpdateInput debtorUpdateInput) {

        return debtorService.update(debtorUpdateInput);
    }

    @MutationMapping(name = "deleteDebtorById")
    public Boolean deleteById (@Argument Long id) {
        return debtorService.deleteById(id);
    }


}
