package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.entity.Category;
import com.habsida.moragoproject.entity.File;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ThemeDTO {
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
