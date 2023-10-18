package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.*;
import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.payload.PasswordResetPayload;
import com.habsida.moragoproject.service.PasswordResetService;
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

    @QueryMapping(name = "getPasswordResetsByPaging")
    public Page<PasswordReset> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return passwordResetService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createPasswordReset")
    public PasswordReset create (@Valid @Argument(name = "passwordResetInput") PasswordResetCreateInput passwordResetCreateInput) {
        return passwordResetService.create(passwordResetCreateInput);
    }

    @MutationMapping(name = "updatePasswordReset")
    public PasswordReset update (@Valid @Argument(name = "passwordResetInput") PasswordResetUpdateInput passwordResetUpdateInput) {
        return passwordResetService.update(passwordResetUpdateInput);
    }

    @MutationMapping(name = "deletePasswordResetById")
    public Boolean deleteById (@Argument Long id) {
        return passwordResetService.deleteById(id);
    }

    @MutationMapping(name = "requestPasswordReset")
    public PasswordResetPayload requestPasswordReset(@Argument(name = "phoneInput") String phone) {
        return passwordResetService.requestPasswordReset(phone);
    }

    @QueryMapping(name = "checkResetCodeHash")
    public String checkResetCodeHash(@Argument(name = "resetCodeHashInput") ResetCodeHashInput resetCodeHashInput) {
        return passwordResetService.checkResetCodeHash(resetCodeHashInput);
    }

}
