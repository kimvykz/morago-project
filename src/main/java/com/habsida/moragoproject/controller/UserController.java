package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.*;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.payload.Profile;
import com.habsida.moragoproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Slf4j
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
    //@PostAuthorize("returnObject.phone == authentication.name")
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
    public User create (@Valid @Argument(name = "userInput") UserCreateInput userCreateInput) {
        return userService.create(userCreateInput);
    }

    @MutationMapping(name = "updateUser")
    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    public User update (@Valid @Argument(name = "userInput") UserUpdateInput userUpdateInput) {
        return userService.update(userUpdateInput);
    }

    @MutationMapping(name = "deleteUserById")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Boolean deleteById (@Argument Long id) {
        return userService.deleteById(id);
    }

    @MutationMapping(name = "updateUserRolesByUserId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User updateUserRolesByUserId(@Argument(name = "userRolesInput") UserRolesUpdateInput userRolesUpdateInput) {
        return userService.updateRolesByUserId(userRolesUpdateInput);
    }

    @MutationMapping(name = "updateUserApnTokenByUserId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public User updateUserApnTokenByUserId(@Argument(name = "userApnTokenInput") UserApnTokenUpdateInput userApnTokenUpdateInput) {
        return userService.updateApnTokenByUserId(userApnTokenUpdateInput);
    }

    @MutationMapping(name = "updateUserFcmTokenByUserId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public User updateUserFcmTokenByUserId(@Argument(name = "userFcmTokenInput") UserFcmTokenUpdateInput userFcmTokenUpdateInput) {
        return userService.updateFcmTokenByUserId(userFcmTokenUpdateInput);
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

    @QueryMapping(name = "getCurrentUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public User getCurrentUser () {
        log.info("UserController.getCurrentUser - Query Mapping");
        return userService.getCurrentUser();
    }

    @SchemaMapping(typeName = "User", field = "profile")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public Profile getProfile(User user) {
        log.info("UserController.getProfile - query to SchemaMapping");
        return userService.getProfile(user);
    }

}
