package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Notification;
import com.habsida.moragoproject.repository.NotificationRepository;
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
    public List<Notification> getAllItems() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getItemById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Notification is not found for the id - " + id );
        });
    }

    @Override
    public Notification createItem(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification modifyItem(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void removeItem(Long id) {
        notificationRepository.deleteById(id);
    }
}
