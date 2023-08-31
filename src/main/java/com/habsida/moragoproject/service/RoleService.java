package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllItems();
    Role getItemById(Long id);
    Role createItem(Role role);
    Role modifyItem(Role role);
    void removeItem(Long id);
    Role getItemByName(String name);
}
