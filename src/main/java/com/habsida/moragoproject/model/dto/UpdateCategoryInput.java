package com.habsida.moragoproject.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateCategoryInput {
        private Boolean isActive;
        private String name;
}