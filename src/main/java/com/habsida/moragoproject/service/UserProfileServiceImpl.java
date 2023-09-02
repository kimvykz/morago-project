package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.UserProfile;
import com.habsida.moragoproject.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    private UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl (UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public List<UserProfile> getAll () {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile getById (Long id) {
        return userProfileRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("UserProfile is not found for the id - " + id);
        });
    }

    @Override
    public UserProfile create (UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update (UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteById(Long id) {
        userProfileRepository.deleteById(id);
    }

}
