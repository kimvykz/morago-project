package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface NotificationService {
    List<Notification> getAll();
    Page<Notification> getAllPaged(PageRequest pageRequest);
    Notification getById(Long id);
    Notification create(Notification notification);
    Notification update(Notification notification);
    Boolean deleteById(Long id);
}
