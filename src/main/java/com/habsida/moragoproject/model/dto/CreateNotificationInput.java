package com.habsida.moragoproject.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Getter
@Setter
public class CreateNotificationInput {
    private LocalDate date;
    private String text;
    private LocalTime time;
    private String title;

    //private User user;
}
