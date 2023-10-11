package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.*;
import com.habsida.moragoproject.model.payload.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    List<User> getAll ();
    Page<User> getAllByPaging (PageRequest pageRequest);
    User getById (Long id);
    User create (UserCreateInput userCreateInput);
    User update (UserUpdateInput userUpdateInput);
    Boolean deleteById (Long id);
    User getByPhone(String phone);
    Boolean isExistsByPhone (String phone);
    User updateRolesByUserId (UserRolesUpdateInput userRolesUpdateInput);
    User updateApnTokenByUserId(UserApnTokenUpdateInput userApnTokenUpdateInput);
    User updateFcmTokenByUserId(UserFcmTokenUpdateInput userFcmTokenUpdateInput);
    User deleteApnTokenByUserId(Long id);
    User deleteFcmTokenByUserId(Long id);
    User getCurrentUser();
    Profile getProfile(User user);
    User updateIsAvailable(Boolean isAvailable);
    User addFundsToBalance(Float addFunds);
}
