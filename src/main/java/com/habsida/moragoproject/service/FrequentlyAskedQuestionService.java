package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.FrequentlyAskedQuestion;

import java.util.List;

public interface FrequentlyAskedQuestionService {
    List<FrequentlyAskedQuestion> getAllItems();
    FrequentlyAskedQuestion getItemById(Long id);
    FrequentlyAskedQuestion createItem(FrequentlyAskedQuestion frequentlyAskedQuestion);
    FrequentlyAskedQuestion modifyItem(FrequentlyAskedQuestion frequentlyAskedQuestion);
    void removeItem(Long id);
}
