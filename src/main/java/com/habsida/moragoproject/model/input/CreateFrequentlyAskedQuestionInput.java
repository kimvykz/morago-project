package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.enums.EFAQCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CreateFrequentlyAskedQuestionInput {

    private String answer;
    private EFAQCategory category;
    private String question;
}
