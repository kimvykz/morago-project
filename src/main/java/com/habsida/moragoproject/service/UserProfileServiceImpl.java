package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.UserProfileCreateInput;
import com.habsida.moragoproject.model.input.UserProfileUpdateInput;
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
    public Page<UserProfile> getAllByPaging (PageRequest pageRequest) {
        return userProfileRepository.findAll(pageRequest);
    }

    @Override
    public UserProfile getById (Long id) {
        return userProfileRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("UserProfile is not found for the id - " + id);
        });
    }

    @Override
    public UserProfile create (UserProfileCreateInput userProfileCreateInput) {
        UserProfile userProfile = new UserProfile();

        if (userProfileCreateInput.getIsFreeCallMade() == null) {
            throw new IllegalArgumentException("field isFreeCallMade cannot be null");
        } else {
            userProfile.setIsFreeCallMade(userProfileCreateInput.getIsFreeCallMade());
        }
        if (userProfileCreateInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            userProfile.setUser(userProfileCreateInput.getUser());
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update (UserProfileUpdateInput userProfileUpdateInput) {
        UserProfile userProfile = getById(userProfileUpdateInput.getId());

        if (userProfileUpdateInput.getIsFreeCallMade() != null
            && !userProfile.getIsFreeCallMade().equals(userProfileUpdateInput.getIsFreeCallMade())) {
            userProfile.setIsFreeCallMade(userProfileUpdateInput.getIsFreeCallMade());
        }
        if (userProfileUpdateInput.getUser() != null
            && !userProfile.getUser().equals(userProfileUpdateInput.getUser())) {
            userProfile.setUser(userProfileUpdateInput.getUser());
        }
        return userProfileRepository.save(userProfile);
    }

    @Override
    public Boolean deleteById (Long id) {
        userProfileRepository.deleteById(id);
        return true;
    }

}
