package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.model.input.CreateFrequentlyAskedQuestionInput;
import com.habsida.moragoproject.model.input.UpdateFrequentlyAskedQuestionInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface FrequentlyAskedQuestionService {
    List<FrequentlyAskedQuestion> getAll();
    Page<FrequentlyAskedQuestion> getAllPaged(PageRequest pageRequest);
    FrequentlyAskedQuestion getById(Long id);
    FrequentlyAskedQuestion create(CreateFrequentlyAskedQuestionInput createFrequentlyAskedQuestionInput);
    FrequentlyAskedQuestion update(UpdateFrequentlyAskedQuestionInput updateFrequentlyAskedQuestionInput);
    Boolean deleteById(Long id);
}
