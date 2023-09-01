package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Role;
import com.habsida.moragoproject.enums.ERole;
import com.habsida.moragoproject.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllItems() {
        return roleRepository.findAll();
    }

    @Override
    public Role getItemById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()){
            return role.get();
        }
        throw new RuntimeException("Role is not found for the id - " + id);
    }

    @Override
    public Role createItem(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role modifyItem(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void removeItem(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getItemByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()){
            return role.get();
        }
        return new Role();
    }
}
