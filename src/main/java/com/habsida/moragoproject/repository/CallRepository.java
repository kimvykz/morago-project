package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.entity.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, Long> {
}