package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.enums.FAQCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class FrequentlyAskedQuestionDTO {

    private String answer;
    private FAQCategory category;
    private String question;
}
