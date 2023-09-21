package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.model.input.FrequentlyAskedQuestionCreateInput;
import com.habsida.moragoproject.model.input.FrequentlyAskedQuestionUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface FrequentlyAskedQuestionService {
    List<FrequentlyAskedQuestion> getAll ();
    Page<FrequentlyAskedQuestion> getAllByPaging (PageRequest pageRequest);
    FrequentlyAskedQuestion getById (Long id);
    FrequentlyAskedQuestion create (FrequentlyAskedQuestionCreateInput frequentlyAskedQuestionCreateInput);
    FrequentlyAskedQuestion update (FrequentlyAskedQuestionUpdateInput frequentlyAskedQuestionUpdateInput);
    Boolean deleteById (Long id);
}
