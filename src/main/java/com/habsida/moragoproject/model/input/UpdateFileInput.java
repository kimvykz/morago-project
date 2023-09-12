package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UpdateFileInput {
    private Long id;
    private String originalTitle;
    private String path;
    private String type;

    //private User user;

    //private Theme theme;
}
