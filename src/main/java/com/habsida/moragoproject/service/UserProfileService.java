package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAll ();
    UserProfile getById (Long id);
    UserProfile create (UserProfile userProfile);
    UserProfile update (UserProfile userProfile);
    void deleteById (Long id);
}
