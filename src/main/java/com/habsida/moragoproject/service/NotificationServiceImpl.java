package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.input.CreateNotificationInput;
import com.habsida.moragoproject.model.input.UpdateNotificationInput;
import com.habsida.moragoproject.repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{
    private NotificationRepository notificationRepository;
    private ModelMapper modelMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
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
    public Notification create(CreateNotificationInput createNotificationInput) {
        Notification notification = modelMapper.map(createNotificationInput, Notification.class);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(UpdateNotificationInput updateNotificationInput) {
        Notification notification = getById(updateNotificationInput.getId());
        modelMapper.map(updateNotificationInput, notification);
        return notificationRepository.save(notification);
    }

    @Override
    public Boolean deleteById(Long id) {
        notificationRepository.deleteById(id);
        return true;
    }
}
