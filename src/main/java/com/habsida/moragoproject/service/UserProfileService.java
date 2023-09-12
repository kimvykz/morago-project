package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.CreateUserProfileInput;
import com.habsida.moragoproject.model.input.UpdateUserProfileInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAll ();
    Page<UserProfile> getAllPaged (PageRequest pageRequest);
    UserProfile getById (Long id);
    UserProfile create (CreateUserProfileInput createUserProfileInput);
    UserProfile update (UpdateUserProfileInput updateUserProfileInput);
    Boolean deleteById (Long id);
}
