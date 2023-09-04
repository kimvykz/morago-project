package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Notification;
import com.habsida.moragoproject.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService{
    private NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Page<Notification> getAllPaged(PageRequest pageRequest) {
        return notificationRepository.findAll(pageRequest);
    }

    @Override
    public Notification getById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Notification is not found for the id - " + id );
        });
    }

    @Override
    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Boolean deleteById(Long id) {
        notificationRepository.deleteById(id);
        return true;
    }
}
