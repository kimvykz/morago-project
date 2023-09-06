package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CreateThemeInput {
    private String description;
    private Boolean isActive;
    private Boolean isPopular;
    private String koreanTitle;
    private String name;
    private Double nightPrice;
    private Double price;

    //private Category category;

    //private List<File> file;
}
