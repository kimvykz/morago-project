package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.UserProfileCreateInput;
import com.habsida.moragoproject.model.input.UserProfileUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAll ();
    Page<UserProfile> getAllByPaging (PageRequest pageRequest);
    UserProfile getById (Long id);
    UserProfile create (UserProfileCreateInput userProfileCreateInput);
    UserProfile update (UserProfileUpdateInput userProfileUpdateInput);
    Boolean deleteById (Long id);
}
