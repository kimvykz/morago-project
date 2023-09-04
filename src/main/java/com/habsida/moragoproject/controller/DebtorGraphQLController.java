package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.DebtorDTO;
import com.habsida.moragoproject.entity.Debtor;
import com.habsida.moragoproject.service.DebtorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class DebtorGraphQLController {
    private ModelMapper modelMapper;

    private DebtorService debtorService;

    public DebtorGraphQLController(ModelMapper modelMapper,
                                   DebtorService debtorService) {
        this.modelMapper = modelMapper;
        this.debtorService = debtorService;
    }

    @QueryMapping(name = "getDebtor")
    public Iterable<Debtor> getAll (){
        return debtorService.getAll();
    }

    @QueryMapping(name = "getDebtorById")
    public Debtor getById (@Argument Long id) {
        return debtorService.getById(id);
    }

    @QueryMapping(name = "getDebtorsPaged")
    public Page<Debtor> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return debtorService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createDebtor")
    public Debtor create (@Valid @Argument(name = "debtor") DebtorDTO debtorDTO) {
        Debtor debtor = modelMapper.map(debtorDTO, Debtor.class);
        return debtorService.create(debtor);
    }

    @MutationMapping(name = "updateDebtorById")
    public Debtor updateById (@Valid @Argument Long id,
                              @Argument(name = "debtor") DebtorDTO debtorDTO) {
        Debtor debtor = debtorService.getById(id);
        modelMapper.map(debtorDTO, debtor);
        return debtorService.update(debtor);
    }

    @MutationMapping(name = "deleteDebtorById")
    public Boolean deleteById (@Argument Long id) {
        return debtorService.deleteById(id);
    }


}
