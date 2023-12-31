package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.UserProfileCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.UserProfileUpdateInput;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.service.UserProfileService;
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

    public UserProfileController (UserProfileService userProfileService) {
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

    @QueryMapping(name = "getUserProfilesByPaging")
    public Page<UserProfile> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return userProfileService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createUserProfile")
    public UserProfile create (@Valid @Argument(name = "userProfileInput") UserProfileCreateInput userProfileCreateInput) {
        return userProfileService.create(userProfileCreateInput);
    }

    @MutationMapping(name = "updateUserProfile")
    public UserProfile update (@Valid @Argument(name = "userProfileInput") UserProfileUpdateInput userProfileUpdateInput) {
        return userProfileService.update(userProfileUpdateInput);
    }

    @MutationMapping(name = "deleteUserProfileById")
    public Boolean deleteById (@Argument Long id) {
        return userProfileService.deleteById(id);
    }


}
