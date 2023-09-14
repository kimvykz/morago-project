package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateUserInput;
import com.habsida.moragoproject.model.input.LoginUserInput;
import com.habsida.moragoproject.model.input.RegistrationUserInput;
import com.habsida.moragoproject.model.input.UpdateUserInput;
import com.habsida.moragoproject.model.payload.LoginPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    List<User> getAll ();
    Page<User> getAllPaged (PageRequest pageRequest);
    User getById (Long id);
    User create (CreateUserInput createUserInput);
    User update (UpdateUserInput updateUserInput);
    Boolean deleteById (Long id);
    User getByPhone(String phone);
    Boolean isExistsByPhone(String phone);

}
