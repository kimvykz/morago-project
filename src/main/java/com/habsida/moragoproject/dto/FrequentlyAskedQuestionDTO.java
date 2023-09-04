package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.enums.EFAQCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class FrequentlyAskedQuestionDTO {

    private String answer;
    private EFAQCategory category;
    private String question;
}
