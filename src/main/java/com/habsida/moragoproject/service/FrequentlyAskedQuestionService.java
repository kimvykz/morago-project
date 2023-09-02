package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.FrequentlyAskedQuestion;

import java.util.List;

public interface FrequentlyAskedQuestionService {
    List<FrequentlyAskedQuestion> getAll();
    FrequentlyAskedQuestion getById(Long id);
    FrequentlyAskedQuestion create(FrequentlyAskedQuestion frequentlyAskedQuestion);
    FrequentlyAskedQuestion update(FrequentlyAskedQuestion frequentlyAskedQuestion);
    void deleteById(Long id);
}
