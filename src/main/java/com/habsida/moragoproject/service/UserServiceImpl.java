package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Role;
import com.habsida.moragoproject.entity.User;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return userRepository.save(user);
    }

    @Override
    public User update (User user) {
        for(Role role : user.getRoles()){
            if (role.getId() == null){
                role.setId(roleService.getByName(role.getName().toString()).getId());
            }
        }
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteById (Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
