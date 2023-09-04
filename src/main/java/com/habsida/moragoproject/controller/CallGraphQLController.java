package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.CallDTO;
import com.habsida.moragoproject.entity.Call;
import com.habsida.moragoproject.service.CallService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class CallGraphQLController {
    private ModelMapper modelMapper;

    private CallService callService;

    public CallGraphQLController(ModelMapper modelMapper,
                                 CallService callService) {
        this.modelMapper = modelMapper;
        this.callService = callService;
    }

    @QueryMapping(name = "getCalls")
    public Iterable<Call> getAll (){
        return callService.getAll();
    }

    @QueryMapping(name = "getCallById")
    public Call getById (@Argument Long id) {
        return callService.getById(id);
    }

    @QueryMapping(name = "getCallsPaged")
    public Page<Call> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return callService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createCall")
    public Call create (@Valid @Argument(name = "call") CallDTO callDTO) {
        Call call = modelMapper.map(callDTO, Call.class);
        return callService.create(call);
    }

    @MutationMapping(name = "updateCallById")
    public Call updateById (@Valid @Argument Long id,
                              @Argument(name = "call") CallDTO callDTO) {
        Call call = callService.getById(id);
        modelMapper.map(callDTO, call);
        return callService.update(call);
    }

    @MutationMapping(name = "deleteCallById")
    public Boolean deleteById (@Argument Long id) {
        return callService.deleteById(id);
    }


}
