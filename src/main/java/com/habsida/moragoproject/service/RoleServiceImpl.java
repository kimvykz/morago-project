package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.CreateRoleInput;
import com.habsida.moragoproject.model.input.UpdateRoleInput;
import com.habsida.moragoproject.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    public RoleServiceImpl (RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Role> getAll () {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> getAllPaged(PageRequest pageRequest) {
        return roleRepository.findAll(pageRequest);
    }

    @Override
    public Role getById (Long id) {
        return roleRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Role is not found for the id - " + id);
        });
    }

    @Override
    public Role create (CreateRoleInput createRoleInput) {
        Role role = modelMapper.map(createRoleInput, Role.class);
        if (role.getName() == null) {
            throw new IllegalArgumentException("field name cannot be null");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role update (UpdateRoleInput updateRoleInput) {
        Role role = getById(updateRoleInput.getId());
        modelMapper.map(updateRoleInput, role);
        if (role.getName() == null) {
            throw new IllegalArgumentException("field name cannot be null");
        }
        return roleRepository.save(role);
    }

    @Override
    public Boolean deleteById (Long id) {
        roleRepository.deleteById(id);
        return true;
    }

    @Override
    public Role getByName (ERole name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()){
            return role.get();
        }
        return null;
    }
}
