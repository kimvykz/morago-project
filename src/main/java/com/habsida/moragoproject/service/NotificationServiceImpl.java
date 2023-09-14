package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateNotificationInput;
import com.habsida.moragoproject.model.input.UpdateNotificationInput;
import com.habsida.moragoproject.repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    public Notification create(CreateNotificationInput createNotificationInput) {
        Notification notification = new Notification();

        if (createNotificationInput.getDate() == null) {
            throw new IllegalArgumentException("field date cannot be null");
        } else {
            notification.setDate(createNotificationInput.getDate());
        }
        if (createNotificationInput.getText() == null
            || createNotificationInput.getText().isBlank()) {
            throw new IllegalArgumentException("field text cannot be null");
        } else {
            notification.setText(createNotificationInput.getText());
        }
        if (createNotificationInput.getTime() == null) {
            throw new IllegalArgumentException("field time cannot be null");
        } else {
            notification.setTime(createNotificationInput.getTime());
        }
        if (createNotificationInput.getTitle() == null
            || createNotificationInput.getTitle().isBlank()) {
            throw new IllegalArgumentException("field title cannot be null");
        } else {
            notification.setTitle(createNotificationInput.getTitle());
        }
        if (createNotificationInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            notification.setUser(createNotificationInput.getUser());
        }
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(UpdateNotificationInput updateNotificationInput) {
        Notification notification = getById(updateNotificationInput.getId());

        if (updateNotificationInput.getDate() != null
            && !notification.getDate().equals(updateNotificationInput.getDate())) {
            notification.setDate(updateNotificationInput.getDate());
        }
        if (updateNotificationInput.getText() != null
            && !updateNotificationInput.getText().isBlank()
            && !notification.getText().equals(updateNotificationInput.getText())) {
            notification.setText(updateNotificationInput.getText());
        }
        if (updateNotificationInput.getTime() != null
            && !notification.getTime().equals(updateNotificationInput.getTime())) {
            notification.setTime(updateNotificationInput.getTime());
        }
        if (updateNotificationInput.getTitle() != null
            && !updateNotificationInput.getTitle().isBlank()
            && !notification.getTitle().equals(updateNotificationInput.getTitle())) {
            notification.setTitle(updateNotificationInput.getTitle());
        }
        if (updateNotificationInput.getUser() != null
            && !notification.getUser().equals(updateNotificationInput.getUser())) {
            notification.setUser(updateNotificationInput.getUser());
        }
        return notificationRepository.save(notification);
    }

    @Override
    public Boolean deleteById(Long id) {
        notificationRepository.deleteById(id);
        return true;
    }
}
