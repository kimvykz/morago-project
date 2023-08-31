package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.entity.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
}
