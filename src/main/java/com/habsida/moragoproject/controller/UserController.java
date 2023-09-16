package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.*;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostAuthorize("returnObject.phone == authentication.name")
    public User getById (@Argument Long id) {
        return userService.getById(id);
    }

    @QueryMapping(name = "getUsersByPaging")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Page<User> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return userService.getAllByPaging(pageRequest);
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

    @MutationMapping(name = "updateUserRolesByUserId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User updateUserRolesByUserId(@Argument(name = "userRolesInput") UpdateUserRolesInput updateUserRolesInput) {
        return userService.updateRolesByUserId(updateUserRolesInput);
    }

    @MutationMapping(name = "updateUserApnTokenByUserId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public User updateUserApnTokenByUserId(@Argument(name = "userApnTokenInput") UpdateUserApnTokenInput updateUserApnTokenInput) {
        return userService.updateApnTokenByUserId(updateUserApnTokenInput);
    }

    @MutationMapping(name = "updateUserFcmTokenByUserId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public User updateUserFcmTokenByUserId(@Argument(name = "userFcmTokenInput") UpdateUserFcmTokenInput updateUserFcmTokenInput) {
        return userService.updateFcmTokenByUserId(updateUserFcmTokenInput);
    }

    @MutationMapping(name = "deleteUserApnTokenByUserId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public User deleteUserApnTokenByUserId(@Argument(name = "userId") Long id) {
        return userService.deleteApnTokenByUserId(id);
    }

    @MutationMapping(name = "deleteUserFcmTokenByUserId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public User deleteUserFcmTokenByUserId(@Argument(name = "userId") Long id) {
        return userService.deleteFcmTokenByUserId(id);
    }

}
