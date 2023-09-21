package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.RoleCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.RoleUpdateInput;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.service.RoleService;
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

    @QueryMapping(name = "getRolesByPaging")
    public Page<Role> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return roleService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createRole")
    public Role create (@Valid @Argument(name = "roleInput") RoleCreateInput roleCreateInput) {
        return roleService.create(roleCreateInput);
    }

    @MutationMapping(name = "updateRole")
    public Role update (@Valid @Argument(name = "roleInput") RoleUpdateInput roleUpdateInput) {
        return roleService.update(roleUpdateInput);
    }

    @MutationMapping(name = "deleteRoleById")
    public Boolean deleteById (@Argument Long id) {
        return roleService.deleteById(id);
    }


}
