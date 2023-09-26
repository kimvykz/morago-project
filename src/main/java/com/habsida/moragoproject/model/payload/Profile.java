package com.habsida.moragoproject.model.payload;


import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.entity.Theme;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class Profile {

    private LocalDate dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;
    private List<Language> languages;
    private List<Theme> themes;
    private Boolean isFreeCallMade;

    private String whoAmI;

}
