package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    List<User> getAll ();
    Page<User> getAllByPaging (PageRequest pageRequest);
    User getById (Long id);
    User create (CreateUserInput createUserInput);
    User update (UpdateUserInput updateUserInput);
    Boolean deleteById (Long id);
    User getByPhone(String phone);
    Boolean isExistsByPhone (String phone);
    User updateRolesByUserId (UpdateUserRolesInput updateUserRolesInput);
    User updateApnTokenByUserId(UpdateUserApnTokenInput updateUserApnTokenInput);
    User updateFcmTokenByUserId(UpdateUserFcmTokenInput updateUserFcmTokenInput);
    User deleteApnTokenByUserId(Long id);
    User deleteFcmTokenByUserId(Long id);

}
