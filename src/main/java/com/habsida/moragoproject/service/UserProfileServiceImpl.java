package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.repository.UserProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserProfile create (UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update (UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public Boolean deleteById(Long id) {
        userProfileRepository.deleteById(id);
        return true;
    }

}
