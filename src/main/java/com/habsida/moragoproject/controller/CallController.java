package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateCallInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.UpdateCallInput;
import com.habsida.moragoproject.model.entity.Call;
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
public class CallController {

    private CallService callService;

    public CallController(CallService callService) {
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

    @QueryMapping(name = "getCallsByPaging")
    public Page<Call> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return callService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createCall")
    public Call create (@Valid @Argument(name = "callInput") CreateCallInput createCallInput) {
        return callService.create(createCallInput);
    }

    @MutationMapping(name = "updateCall")
    public Call update (@Valid @Argument(name = "callInput") UpdateCallInput updateCallInput) {
        return callService.update(updateCallInput);
    }

    @MutationMapping(name = "deleteCallById")
    public Boolean deleteById (@Argument Long id) {
        return callService.deleteById(id);
    }


}
