package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.CreateUserProfileInput;
import com.habsida.moragoproject.model.input.UpdateUserProfileInput;
import com.habsida.moragoproject.repository.UserProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    public UserProfile create (CreateUserProfileInput createUserProfileInput) {
        UserProfile userProfile = new UserProfile();

        if (createUserProfileInput.getIsFreeCallMade() == null) {
            throw new IllegalArgumentException("field isFreeCallMade cannot be null");
        } else {
            userProfile.setIsFreeCallMade(createUserProfileInput.getIsFreeCallMade());
        }
        if (createUserProfileInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            userProfile.setUser(createUserProfileInput.getUser());
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update (UpdateUserProfileInput updateUserProfileInput) {
        UserProfile userProfile = getById(updateUserProfileInput.getId());

        if (updateUserProfileInput.getIsFreeCallMade() != null
            && !userProfile.getIsFreeCallMade().equals(updateUserProfileInput.getIsFreeCallMade())) {
            userProfile.setIsFreeCallMade(updateUserProfileInput.getIsFreeCallMade());
        }
        if (updateUserProfileInput.getUser() != null
            && !userProfile.getUser().equals(updateUserProfileInput.getUser())) {
            userProfile.setUser(updateUserProfileInput.getUser());
        }
        return userProfileRepository.save(userProfile);
    }

    @Override
    public Boolean deleteById (Long id) {
        userProfileRepository.deleteById(id);
        return true;
    }

}
