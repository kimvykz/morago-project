package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
public class TranslatorProfileUpdateInput {
    private Long id;
    private LocalDate dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;

    private User user;

    private List<Language> languages;

    private List<Theme> themes;
}
