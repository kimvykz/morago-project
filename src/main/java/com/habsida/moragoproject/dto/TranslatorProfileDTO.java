package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.entity.Language;
import com.habsida.moragoproject.entity.Theme;
import com.habsida.moragoproject.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
public class TranslatorProfileDTO {

    private String dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;

    //private User user;

    //private List<Language> languages;

    //private List<Theme> themes;
}
