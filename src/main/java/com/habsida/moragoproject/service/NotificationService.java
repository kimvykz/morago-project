package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.input.CreateNotificationInput;
import com.habsida.moragoproject.model.input.UpdateNotificationInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface NotificationService {
    List<Notification> getAll();
    Page<Notification> getAllPaged(PageRequest pageRequest);
    Notification getById(Long id);
    Notification create(CreateNotificationInput createNotificationInput);
    Notification update(UpdateNotificationInput updateNotificationInput);
    Boolean deleteById(Long id);
}
