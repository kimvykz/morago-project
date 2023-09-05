package com.habsida.moragoproject.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CreateTranslatorProfileInput {

    private String dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;

    //private User user;

    //private List<Language> languages;

    //private List<Theme> themes;
}
