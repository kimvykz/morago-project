package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllItems();
    User getItemById(Long id);
    User createItem(User user);
    User modifyItem(User user);
    void removeItem(Long id);
}
