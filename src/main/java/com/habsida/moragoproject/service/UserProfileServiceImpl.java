package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.UserProfile;
import com.habsida.moragoproject.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    private UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public List<UserProfile> getAllItems() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile getItemById(Long id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        if (userProfile.isPresent()){
            return userProfile.get();
        }
        throw new RuntimeException("UserProfile is not found for the id - " + id);
    }

    @Override
    public UserProfile createItem(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile modifyItem(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public void removeItem(Long id) {
        userProfileRepository.deleteById(id);
    }

}
