package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateUserInput;
import com.habsida.moragoproject.model.input.UpdateUserInput;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class UserGraphQLController {
    private ModelMapper modelMapper;

    private UserService userService;

    public UserGraphQLController(ModelMapper modelMapper,
                                 UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @QueryMapping(name = "getUsers")
    public Iterable<User> getAll (){
        return userService.getAll();
    }

    @QueryMapping(name = "getUserById")
    public User getById (@Argument Long id) {
        return userService.getById(id);
    }

    @QueryMapping(name = "getUsersPaged")
    public Page<User> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createUser")
    public User create (@Valid @Argument(name = "user") CreateUserInput createUserInput) {
        User user = modelMapper.map(createUserInput, User.class);
        return userService.create(user);
    }

    @MutationMapping(name = "updateUserById")
    public User updateById (@Valid @Argument Long id,
                              @Argument(name = "user") UpdateUserInput updateUserInput) {
        User user = userService.getById(id);
        modelMapper.map(updateUserInput, user);
        return userService.update(user);
    }

    @MutationMapping(name = "deleteUserById")
    public Boolean deleteById (@Argument Long id) {
        return userService.deleteById(id);
    }


}
