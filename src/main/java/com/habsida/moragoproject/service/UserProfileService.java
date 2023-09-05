package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAll ();
    Page<UserProfile> getAllPaged (PageRequest pageRequest);
    UserProfile getById (Long id);
    UserProfile create (UserProfile userProfile);
    UserProfile update (UserProfile userProfile);
    Boolean deleteById (Long id);
}
