package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.input.NotificationCreateInput;
import com.habsida.moragoproject.model.input.NotificationUpdateInput;
import com.habsida.moragoproject.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{
    private NotificationRepository notificationRepository;

    public NotificationServiceImpl (NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAll () {
        return notificationRepository.findAll();
    }

    @Override
    public Page<Notification> getAllByPaging (PageRequest pageRequest) {
        return notificationRepository.findAll(pageRequest);
    }

    @Override
    public Notification getById (Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Notification is not found for the id - " + id );
        });
    }

    @Override
    public Notification create (NotificationCreateInput notificationCreateInput) {
        Notification notification = new Notification();

        if (notificationCreateInput.getDate() == null) {
            throw new IllegalArgumentException("field date cannot be null");
        } else {
            notification.setDate(notificationCreateInput.getDate());
        }
        if (notificationCreateInput.getText() == null
            || notificationCreateInput.getText().isBlank()) {
            throw new IllegalArgumentException("field text cannot be null");
        } else {
            notification.setText(notificationCreateInput.getText());
        }
        if (notificationCreateInput.getTime() == null) {
            throw new IllegalArgumentException("field time cannot be null");
        } else {
            notification.setTime(notificationCreateInput.getTime());
        }
        if (notificationCreateInput.getTitle() == null
            || notificationCreateInput.getTitle().isBlank()) {
            throw new IllegalArgumentException("field title cannot be null");
        } else {
            notification.setTitle(notificationCreateInput.getTitle());
        }
        if (notificationCreateInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            notification.setUser(notificationCreateInput.getUser());
        }
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update (NotificationUpdateInput notificationUpdateInput) {
        Notification notification = getById(notificationUpdateInput.getId());

        if (notificationUpdateInput.getDate() != null
            && !notification.getDate().equals(notificationUpdateInput.getDate())) {
            notification.setDate(notificationUpdateInput.getDate());
        }
        if (notificationUpdateInput.getText() != null
            && !notificationUpdateInput.getText().isBlank()
            && !notification.getText().equals(notificationUpdateInput.getText())) {
            notification.setText(notificationUpdateInput.getText());
        }
        if (notificationUpdateInput.getTime() != null
            && !notification.getTime().equals(notificationUpdateInput.getTime())) {
            notification.setTime(notificationUpdateInput.getTime());
        }
        if (notificationUpdateInput.getTitle() != null
            && !notificationUpdateInput.getTitle().isBlank()
            && !notification.getTitle().equals(notificationUpdateInput.getTitle())) {
            notification.setTitle(notificationUpdateInput.getTitle());
        }
        if (notificationUpdateInput.getUser() != null
            && !notification.getUser().equals(notificationUpdateInput.getUser())) {
            notification.setUser(notificationUpdateInput.getUser());
        }
        return notificationRepository.save(notification);
    }

    @Override
    public Boolean deleteById (Long id) {
        notificationRepository.deleteById(id);
        return true;
    }
}
