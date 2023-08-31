package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
