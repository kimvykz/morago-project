package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Role;
import com.habsida.moragoproject.entity.User;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllItems() {
        return userRepository.findAll();
    }

    @Override
    public User getItemById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        throw new RuntimeException("User is not found for the id - " + id);
    }

    @Override
    public User createItem(User user) {
        return userRepository.save(user);
    }

    @Override
    public User modifyItem(User user) {
        for(Role role : user.getRoles()){
            if (role.getId() == null){
                role.setId(roleService.getItemByName(role.getName().toString()).getId());
            }
        }
        return userRepository.save(user);
    }

    @Override
    public void removeItem(Long id) {
        userRepository.deleteById(id);
    }
}
