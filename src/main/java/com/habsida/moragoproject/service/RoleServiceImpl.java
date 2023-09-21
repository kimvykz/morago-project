package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.RoleCreateInput;
import com.habsida.moragoproject.model.input.RoleUpdateInput;
import com.habsida.moragoproject.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll () {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> getAllByPaging (PageRequest pageRequest) {
        return roleRepository.findAll(pageRequest);
    }

    @Override
    public Role getById (Long id) {
        return roleRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Role is not found for the id - " + id);
        });
    }

    @Override
    public Role create (RoleCreateInput roleCreateInput) {
        Role role = new Role();

        if (roleCreateInput.getName() == null) {
            throw new IllegalArgumentException("field name cannot be null");
        } else {
            role.setName(roleCreateInput.getName());
        }
        return roleRepository.save(role);
    }

    @Override
    public Role update (RoleUpdateInput roleUpdateInput) {
        Role role = getById(roleUpdateInput.getId());

        if (roleUpdateInput.getName() != null
            && !role.getName().equals(roleUpdateInput.getName())) {
            role.setName(roleUpdateInput.getName());
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
