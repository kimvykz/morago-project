package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.PasswordResetDTO;
import com.habsida.moragoproject.entity.PasswordReset;
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
public class PasswordResetGraphQLController {
    private ModelMapper modelMapper;

    private PasswordResetService passwordResetService;

    public PasswordResetGraphQLController(ModelMapper modelMapper,
                                          PasswordResetService passwordResetService) {
        this.modelMapper = modelMapper;
        this.passwordResetService = passwordResetService;
    }

    @QueryMapping(name = "getPasswordReset")
    public Iterable<PasswordReset> getAll (){
        return passwordResetService.getAll();
    }

    @QueryMapping(name = "getPasswordResetById")
    public PasswordReset getById (@Argument Long id) {
        return passwordResetService.getById(id);
    }

    @QueryMapping(name = "getPasswordResetsPaged")
    public Page<PasswordReset> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return passwordResetService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createPasswordReset")
    public PasswordReset create (@Valid @Argument(name = "passwordReset") PasswordResetDTO passwordResetDTO) {
        PasswordReset passwordReset = modelMapper.map(passwordResetDTO, PasswordReset.class);
        return passwordResetService.create(passwordReset);
    }

    @MutationMapping(name = "updatePasswordResetById")
    public PasswordReset updateById (@Valid @Argument Long id,
                              @Argument(name = "passwordReset") PasswordResetDTO passwordResetDTO) {
        PasswordReset passwordReset = passwordResetService.getById(id);
        modelMapper.map(passwordResetDTO, passwordReset);
        return passwordResetService.update(passwordReset);
    }

    @MutationMapping(name = "deletePasswordResetById")
    public Boolean deleteById (@Argument Long id) {
        return passwordResetService.deleteById(id);
    }


}
