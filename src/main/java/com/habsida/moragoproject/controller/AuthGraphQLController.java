package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateRegisterNewUserInput;
import com.habsida.moragoproject.service.RoleService;
import com.habsida.moragoproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.management.openmbean.KeyAlreadyExistsException;

@Controller
public class AuthGraphQLController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public AuthGraphQLController (AuthenticationManager authenticationManager,
                                 UserService userService,
                                 RoleService roleService,
                                 PasswordEncoder passwordEncoder,
                                 ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @MutationMapping(name = "registerNewUser")
    public User registerNewUser (@Argument(name = "newUser")CreateRegisterNewUserInput createRegisterNewUserInput) {
        if (userService.isExistsByPhone(createRegisterNewUserInput.getPhone())) {
            throw new KeyAlreadyExistsException("User is already existed with phone - "
                    + createRegisterNewUserInput.getPhone());
        }
        User user = modelMapper.map(createRegisterNewUserInput, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.create(user);

    }
}
