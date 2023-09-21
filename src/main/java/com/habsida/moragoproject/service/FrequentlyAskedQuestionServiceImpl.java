package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.model.input.FrequentlyAskedQuestionCreateInput;
import com.habsida.moragoproject.model.input.FrequentlyAskedQuestionUpdateInput;
import com.habsida.moragoproject.repository.FrequentlyAskedQuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrequentlyAskedQuestionServiceImpl implements FrequentlyAskedQuestionService{
    private FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;

    public FrequentlyAskedQuestionServiceImpl (FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository) {
        this.frequentlyAskedQuestionRepository = frequentlyAskedQuestionRepository;
    }

    @Override
    public List<FrequentlyAskedQuestion> getAll () {
        return frequentlyAskedQuestionRepository.findAll();
    }

    @Override
    public Page<FrequentlyAskedQuestion> getAllByPaging (PageRequest pageRequest) {
        return frequentlyAskedQuestionRepository.findAll(pageRequest);
    }

    @Override
    public FrequentlyAskedQuestion getById (Long id) {
        return frequentlyAskedQuestionRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("FrequentlyAskedQuestion is not found for the id - " + id);
        });
    }

    @Override
    public FrequentlyAskedQuestion create (FrequentlyAskedQuestionCreateInput frequentlyAskedQuestionCreateInput) {
        FrequentlyAskedQuestion frequentlyAskedQuestion =
                new FrequentlyAskedQuestion();

        if (frequentlyAskedQuestionCreateInput.getAnswer() == null
            || frequentlyAskedQuestionCreateInput.getAnswer().isBlank()) {
            throw new IllegalArgumentException("field answer cannot be null");
        } else {
            frequentlyAskedQuestion.setAnswer(frequentlyAskedQuestionCreateInput.getAnswer());
        }
        if (frequentlyAskedQuestionCreateInput.getCategory() == null) {
            throw new IllegalArgumentException("field category cannot be null");
        } else {
            frequentlyAskedQuestion.setCategory(frequentlyAskedQuestionCreateInput.getCategory());
        }
        if (frequentlyAskedQuestionCreateInput.getQuestion() == null
            || frequentlyAskedQuestionCreateInput.getQuestion().isBlank()) {
            throw new IllegalArgumentException("field question cannot be null");
        } else {
            frequentlyAskedQuestion.setQuestion(frequentlyAskedQuestionCreateInput.getQuestion());
        }

        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Override
    public FrequentlyAskedQuestion update (FrequentlyAskedQuestionUpdateInput frequentlyAskedQuestionUpdateInput) {
        FrequentlyAskedQuestion frequentlyAskedQuestion =
                getById(frequentlyAskedQuestionUpdateInput.getId());

        if (frequentlyAskedQuestionUpdateInput.getAnswer() != null
            && !frequentlyAskedQuestionUpdateInput.getQuestion().isBlank()
            && !frequentlyAskedQuestion.getQuestion().equals(frequentlyAskedQuestionUpdateInput.getQuestion())) {
            frequentlyAskedQuestion.setQuestion(frequentlyAskedQuestionUpdateInput.getQuestion());
        }
        if (frequentlyAskedQuestionUpdateInput.getCategory() != null
            && !frequentlyAskedQuestion.getCategory().equals(frequentlyAskedQuestionUpdateInput.getCategory())) {
            frequentlyAskedQuestion.setCategory(frequentlyAskedQuestionUpdateInput.getCategory());
        }
        if (frequentlyAskedQuestionUpdateInput.getQuestion() != null
            && !frequentlyAskedQuestion.getQuestion().equals(frequentlyAskedQuestionUpdateInput.getQuestion())
            && !frequentlyAskedQuestionUpdateInput.getQuestion().isBlank()) {
            frequentlyAskedQuestion.setQuestion(frequentlyAskedQuestionUpdateInput.getQuestion());
        }

        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Override
    public Boolean deleteById (Long id) {
        frequentlyAskedQuestionRepository.deleteById(id);
        return true;
    }
}
