package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateUserProfileInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.UpdateUserProfileInput;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.service.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class UserProfileController {

    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @QueryMapping(name = "getUserProfiles")
    public Iterable<UserProfile> getAll (){
        return userProfileService.getAll();
    }

    @QueryMapping(name = "getUserProfileById")
    public UserProfile getById (@Argument Long id) {
        return userProfileService.getById(id);
    }

    @QueryMapping(name = "getUserProfilesPaged")
    public Page<UserProfile> getAllPaged (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return userProfileService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createUserProfile")
    public UserProfile create (@Valid @Argument(name = "userProfileInput") CreateUserProfileInput createUserProfileInput) {
        return userProfileService.create(createUserProfileInput);
    }

    @MutationMapping(name = "updateUserProfile")
    public UserProfile update (@Valid @Argument(name = "userProfileInput") UpdateUserProfileInput updateUserProfileInput) {
        return userProfileService.update(updateUserProfileInput);
    }

    @MutationMapping(name = "deleteUserProfileById")
    public Boolean deleteById (@Argument Long id) {
        return userProfileService.deleteById(id);
    }


}
