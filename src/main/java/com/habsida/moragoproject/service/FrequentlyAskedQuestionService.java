package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface FrequentlyAskedQuestionService {
    List<FrequentlyAskedQuestion> getAll();
    Page<FrequentlyAskedQuestion> getAllPaged(PageRequest pageRequest);
    FrequentlyAskedQuestion getById(Long id);
    FrequentlyAskedQuestion create(FrequentlyAskedQuestion frequentlyAskedQuestion);
    FrequentlyAskedQuestion update(FrequentlyAskedQuestion frequentlyAskedQuestion);
    Boolean deleteById(Long id);
}
