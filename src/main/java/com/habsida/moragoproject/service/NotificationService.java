package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllItems();
    Notification getItemById(Long id);
    Notification createItem(Notification notification);
    Notification modifyItem(Notification notification);
    void removeItem(Long id);
}
