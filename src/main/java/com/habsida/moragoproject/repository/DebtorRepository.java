package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.entity.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtorRepository extends JpaRepository<Debtor, Long> {
}
