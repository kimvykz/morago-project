package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class FileUpdateInput {
    private Long id;
    private String originalTitle;
    private String path;
    private String type;
}
