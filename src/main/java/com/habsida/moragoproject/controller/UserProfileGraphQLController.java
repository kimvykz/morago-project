package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateUserProfileInput;
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
public class UserProfileGraphQLController {
    private ModelMapper modelMapper;

    private UserProfileService userProfileService;

    public UserProfileGraphQLController(ModelMapper modelMapper,
                                        UserProfileService userProfileService) {
        this.modelMapper = modelMapper;
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
    public Page<UserProfile> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userProfileService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createUserProfile")
    public UserProfile create (@Valid @Argument(name = "userProfile") CreateUserProfileInput createUserProfileInput) {
        UserProfile userProfile = modelMapper.map(createUserProfileInput, UserProfile.class);
        return userProfileService.create(userProfile);
    }

    @MutationMapping(name = "updateUserProfileById")
    public UserProfile updateById (@Valid @Argument Long id,
                              @Argument(name = "userProfile") UpdateUserProfileInput updateUserProfileInput) {
        UserProfile userProfile = userProfileService.getById(id);
        modelMapper.map(updateUserProfileInput, userProfile);
        return userProfileService.update(userProfile);
    }

    @MutationMapping(name = "deleteUserProfileById")
    public Boolean deleteById (@Argument Long id) {
        return userProfileService.deleteById(id);
    }


}
