package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreatePasswordResetInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.UpdatePasswordResetInput;
import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.service.PasswordResetService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class PasswordResetController {

    private PasswordResetService passwordResetService;

    public PasswordResetController (PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @QueryMapping(name = "getPasswordResets")
    public Iterable<PasswordReset> getAll (){
        return passwordResetService.getAll();
    }

    @QueryMapping(name = "getPasswordResetById")
    public PasswordReset getById (@Argument Long id) {
        return passwordResetService.getById(id);
    }

    @QueryMapping(name = "getPasswordResetsPaged")
    public Page<PasswordReset> getAllPaged (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return passwordResetService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createPasswordReset")
    public PasswordReset create (@Valid @Argument(name = "passwordResetInput") CreatePasswordResetInput createPasswordResetInput) {
        return passwordResetService.create(createPasswordResetInput);
    }

    @MutationMapping(name = "updatePasswordReset")
    public PasswordReset update (@Valid @Argument(name = "passwordResetInput") UpdatePasswordResetInput updatePasswordResetInput) {
        return passwordResetService.update(updatePasswordResetInput);
    }

    @MutationMapping(name = "deletePasswordResetById")
    public Boolean deleteById (@Argument Long id) {
        return passwordResetService.deleteById(id);
    }


}
