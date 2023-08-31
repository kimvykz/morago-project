package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAllItems();
    UserProfile getItemById(Long id);
    UserProfile createItem(UserProfile userProfile);
    UserProfile modifyItem(UserProfile userProfile);
    void removeItem(Long id);
}
