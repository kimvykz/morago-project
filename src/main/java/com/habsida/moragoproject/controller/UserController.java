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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping(name = "getUsers")
    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    public Iterable<User> getAll (){
        return userService.getAll();
    }

    @QueryMapping(name = "getUserById")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostAuthorize("returnObject.phone == authentication.name")
    public User getById (@Argument Long id) {
        return userService.getById(id);
    }

    @QueryMapping(name = "getUsersPaged")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Page<User> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User create (@Valid @Argument(name = "userInput") CreateUserInput createUserInput) {
        return userService.create(createUserInput);
    }

    @MutationMapping(name = "updateUser")
    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    public User update (@Valid @Argument(name = "userInput") UpdateUserInput updateUserInput) {
        return userService.update(updateUserInput);
    }

    @MutationMapping(name = "deleteUserById")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Boolean deleteById (@Argument Long id) {
        return userService.deleteById(id);
    }


}
