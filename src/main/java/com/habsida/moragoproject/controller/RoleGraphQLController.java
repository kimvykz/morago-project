package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.RoleDTO;
import com.habsida.moragoproject.entity.Role;
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
public class RoleGraphQLController {
    private ModelMapper modelMapper;

    private RoleService roleService;

    public RoleGraphQLController(ModelMapper modelMapper,
                                 RoleService roleService) {
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @QueryMapping(name = "getRole")
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
    public Role create (@Valid @Argument(name = "role") RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        return roleService.create(role);
    }

    @MutationMapping(name = "updateRoleById")
    public Role updateById (@Valid @Argument Long id,
                              @Argument(name = "role") RoleDTO roleDTO) {
        Role role = roleService.getById(id);
        modelMapper.map(roleDTO, role);
        return roleService.update(role);
    }

    @MutationMapping(name = "deleteRoleById")
    public Boolean deleteById (@Argument Long id) {
        return roleService.deleteById(id);
    }


}
