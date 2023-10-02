package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class File64CreateInput {
    private String originalTitle;
    private String path;
    private String type;
    private String base64;
}
