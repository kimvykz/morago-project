package com.habsida.moragoproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class FileDTO {
    private String originalTitle;
    private String path;
    private String type;

    //private User user;

    //private Theme theme;
}
