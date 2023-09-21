package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryUpdateInput {
        private Long id;
        private Boolean isActive;
        private String name;
}
