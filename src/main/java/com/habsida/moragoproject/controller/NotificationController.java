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
    private ModelMapper modelMapper;

    private NotificationService notificationService;

    public NotificationController(ModelMapper modelMapper,
                                  NotificationService notificationService) {
        this.modelMapper = modelMapper;
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
    public Notification create (@Valid @Argument(name = "notification") CreateNotificationInput createNotificationInput) {
        Notification notification = modelMapper.map(createNotificationInput, Notification.class);
        return notificationService.create(notification);
    }

    @MutationMapping(name = "updateNotificationById")
    public Notification updateById (@Valid @Argument Long id,
                              @Argument(name = "notification") UpdateNotificationInput updateNotificationInput) {
        Notification notification = notificationService.getById(id);
        modelMapper.map(updateNotificationInput, notification);
        return notificationService.update(notification);
    }

    @MutationMapping(name = "deleteNotificationById")
    public Boolean deleteById (@Argument Long id) {
        return notificationService.deleteById(id);
    }


}
