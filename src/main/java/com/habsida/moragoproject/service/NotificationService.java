package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAll();
    Notification getById(Long id);
    Notification create(Notification notification);
    Notification update(Notification notification);
    void deleteById(Long id);
}
