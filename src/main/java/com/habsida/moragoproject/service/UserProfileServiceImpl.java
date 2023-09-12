package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.CreateUserProfileInput;
import com.habsida.moragoproject.model.input.UpdateUserProfileInput;
import com.habsida.moragoproject.repository.UserProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    private UserProfileRepository userProfileRepository;
    private ModelMapper modelMapper;

    public UserProfileServiceImpl (UserProfileRepository userProfileRepository, ModelMapper modelMapper) {
        this.userProfileRepository = userProfileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserProfile> getAll () {
        return userProfileRepository.findAll();
    }

    @Override
    public Page<UserProfile> getAllPaged(PageRequest pageRequest) {
        return userProfileRepository.findAll(pageRequest);
    }

    @Override
    public UserProfile getById (Long id) {
        return userProfileRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("UserProfile is not found for the id - " + id);
        });
    }

    @Override
    public UserProfile create (CreateUserProfileInput createUserProfileInput) {
        UserProfile userProfile = modelMapper.map(createUserProfileInput, UserProfile.class);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update (UpdateUserProfileInput updateUserProfileInput) {
        UserProfile userProfile = getById(updateUserProfileInput.getId());
        modelMapper.map(updateUserProfileInput, userProfile);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public Boolean deleteById(Long id) {
        userProfileRepository.deleteById(id);
        return true;
    }

}
