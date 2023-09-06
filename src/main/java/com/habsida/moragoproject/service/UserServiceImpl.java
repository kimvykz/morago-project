package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleService roleService;

    public UserServiceImpl (UserRepository userRepository,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAll () {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllPaged(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User getById (Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("User is not found for the id - " + id);
        });
    }

    @Override
    public User create (User user) {
        List<Role> managedRoles = new ArrayList<>();
        for(Role role : user.getRoles()) {
            if (role.getId() == null ){
                Role existRole = roleService.getByName(role.getName());
                if (existRole != null) {
                    managedRoles.add(existRole);
                } else {
                    managedRoles.add(role);
                }
            }
        }
        user.setRoles(managedRoles);
        return userRepository.save(user);
    }

    @Override
    public User update (User user) {
        List<Role> managedRoles = new ArrayList<>();
        for(Role role : user.getRoles()) {
            if (role.getId() == null ){
                Role existRole = roleService.getByName(role.getName());
                if (existRole != null) {
                    managedRoles.add(existRole);
                } else {
                    managedRoles.add(role);
                }
            }
        }
        user.setRoles(managedRoles);
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteById (Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Boolean isExistsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }
}
