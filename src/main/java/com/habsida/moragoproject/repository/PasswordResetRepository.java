package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.entity.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {
}
