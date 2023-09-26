package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Theme;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CategoryCreateInput {
        private Boolean isActive;
        private String name;

        List<Theme> themes;
}
