package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    List<User> getAll ();
    Page<User> getAllPaged (PageRequest pageRequest);
    User getById (Long id);
    User create (User user);
    User update (User user);
    Boolean deleteById (Long id);
}
