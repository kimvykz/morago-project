package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.UserProfileDTO;
import com.habsida.moragoproject.entity.UserProfile;
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

    @QueryMapping(name = "getUserProfile")
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
    public UserProfile create (@Valid @Argument(name = "userProfile") UserProfileDTO userProfileDTO) {
        UserProfile userProfile = modelMapper.map(userProfileDTO, UserProfile.class);
        return userProfileService.create(userProfile);
    }

    @MutationMapping(name = "updateUserProfileById")
    public UserProfile updateById (@Valid @Argument Long id,
                              @Argument(name = "userProfile") UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileService.getById(id);
        modelMapper.map(userProfileDTO, userProfile);
        return userProfileService.update(userProfile);
    }

    @MutationMapping(name = "deleteUserProfileById")
    public Boolean deleteById (@Argument Long id) {
        return userProfileService.deleteById(id);
    }


}