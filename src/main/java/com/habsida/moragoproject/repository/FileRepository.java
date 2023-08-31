package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
