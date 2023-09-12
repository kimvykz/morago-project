package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateNotificationInput;
import com.habsida.moragoproject.model.input.UpdateNotificationInput;
import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @QueryMapping(name = "getNotifications")
    public Iterable<Notification> getAll (){
        return notificationService.getAll();
    }

    @QueryMapping(name = "getNotificationById")
    public Notification getById (@Argument Long id) {
        return notificationService.getById(id);
    }

    @QueryMapping(name = "getNotificationsPaged")
    public Page<Notification> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return notificationService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createNotification")
    public Notification create (@Valid @Argument(name = "notificationInput") CreateNotificationInput createNotificationInput) {
        return notificationService.create(createNotificationInput);
    }

    @MutationMapping(name = "updateNotification")
    public Notification update (@Valid @Argument(name = "notificationInput") UpdateNotificationInput updateNotificationInput) {
        return notificationService.update(updateNotificationInput);
    }

    @MutationMapping(name = "deleteNotificationById")
    public Boolean deleteById (@Argument Long id) {
        return notificationService.deleteById(id);
    }


}
