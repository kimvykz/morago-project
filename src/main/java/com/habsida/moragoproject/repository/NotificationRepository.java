package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
