package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.input.NotificationCreateInput;
import com.habsida.moragoproject.model.input.NotificationUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface NotificationService {
    List<Notification> getAll ();
    Page<Notification> getAllByPaging (PageRequest pageRequest);
    Notification getById (Long id);
    Notification create (NotificationCreateInput notificationCreateInput);
    Notification update (NotificationUpdateInput notificationUpdateInput);
    Boolean deleteById (Long id);
}
