package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateRoleInput;
import com.habsida.moragoproject.model.input.UpdateRoleInput;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @QueryMapping(name = "getRoles")
    public Iterable<Role> getAll (){
        return roleService.getAll();
    }

    @QueryMapping(name = "getRoleById")
    public Role getById (@Argument Long id) {
        return roleService.getById(id);
    }

    @QueryMapping(name = "getRolesPaged")
    public Page<Role> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return roleService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createRole")
    public Role create (@Valid @Argument(name = "roleInput") CreateRoleInput createRoleInput) {
        return roleService.create(createRoleInput);
    }

    @MutationMapping(name = "updateRole")
    public Role update (@Valid @Argument(name = "roleInput") UpdateRoleInput updateRoleInput) {
        return roleService.update(updateRoleInput);
    }

    @MutationMapping(name = "deleteRoleById")
    public Boolean deleteById (@Argument Long id) {
        return roleService.deleteById(id);
    }


}
