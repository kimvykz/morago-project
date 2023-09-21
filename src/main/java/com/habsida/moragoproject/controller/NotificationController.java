package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.NotificationCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.NotificationUpdateInput;
import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.service.NotificationService;
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

    @QueryMapping(name = "getNotificationsByPaging")
    public Page<Notification> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return notificationService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createNotification")
    public Notification create (@Valid @Argument(name = "notificationInput") NotificationCreateInput notificationCreateInput) {
        return notificationService.create(notificationCreateInput);
    }

    @MutationMapping(name = "updateNotification")
    public Notification update (@Valid @Argument(name = "notificationInput") NotificationUpdateInput notificationUpdateInput) {
        return notificationService.update(notificationUpdateInput);
    }

    @MutationMapping(name = "deleteNotificationById")
    public Boolean deleteById (@Argument Long id) {
        return notificationService.deleteById(id);
    }


}
