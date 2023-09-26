package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.entity.File;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class ThemeCreateInput {
    private String description;
    private Boolean isActive;
    private Boolean isPopular;
    private String koreanTitle;
    private String name;
    private Double nightPrice;
    private Double price;

    private Category category;

    private File icon;
}
