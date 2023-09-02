package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll ();
    User getById (Long id);
    User create (User user);
    User update (User user);
    void deleteById (Long id);
}
