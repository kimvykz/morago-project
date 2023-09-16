package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.model.enums.EFAQCategory;
import com.habsida.moragoproject.model.input.CreateFrequentlyAskedQuestionInput;
import com.habsida.moragoproject.model.input.UpdateFrequentlyAskedQuestionInput;
import com.habsida.moragoproject.repository.FrequentlyAskedQuestionRepository;
import org.modelmapper.ModelMapper;
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
    public FrequentlyAskedQuestion create (CreateFrequentlyAskedQuestionInput createFrequentlyAskedQuestionInput) {
        FrequentlyAskedQuestion frequentlyAskedQuestion =
                new FrequentlyAskedQuestion();

        if (createFrequentlyAskedQuestionInput.getAnswer() == null
            || createFrequentlyAskedQuestionInput.getAnswer().isBlank()) {
            throw new IllegalArgumentException("field answer cannot be null");
        } else {
            frequentlyAskedQuestion.setAnswer(createFrequentlyAskedQuestionInput.getAnswer());
        }
        if (createFrequentlyAskedQuestionInput.getCategory() == null) {
            throw new IllegalArgumentException("field category cannot be null");
        } else {
            frequentlyAskedQuestion.setCategory(createFrequentlyAskedQuestionInput.getCategory());
        }
        if (createFrequentlyAskedQuestionInput.getQuestion() == null
            || createFrequentlyAskedQuestionInput.getQuestion().isBlank()) {
            throw new IllegalArgumentException("field question cannot be null");
        } else {
            frequentlyAskedQuestion.setQuestion(createFrequentlyAskedQuestionInput.getQuestion());
        }

        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Override
    public FrequentlyAskedQuestion update (UpdateFrequentlyAskedQuestionInput updateFrequentlyAskedQuestionInput) {
        FrequentlyAskedQuestion frequentlyAskedQuestion =
                getById(updateFrequentlyAskedQuestionInput.getId());

        if (updateFrequentlyAskedQuestionInput.getAnswer() != null
            && !updateFrequentlyAskedQuestionInput.getQuestion().isBlank()
            && !frequentlyAskedQuestion.getQuestion().equals(updateFrequentlyAskedQuestionInput.getQuestion())) {
            frequentlyAskedQuestion.setQuestion(updateFrequentlyAskedQuestionInput.getQuestion());
        }
        if (updateFrequentlyAskedQuestionInput.getCategory() != null
            && !frequentlyAskedQuestion.getCategory().equals(updateFrequentlyAskedQuestionInput.getCategory())) {
            frequentlyAskedQuestion.setCategory(updateFrequentlyAskedQuestionInput.getCategory());
        }
        if (updateFrequentlyAskedQuestionInput.getQuestion() != null
            && !frequentlyAskedQuestion.getQuestion().equals(updateFrequentlyAskedQuestionInput.getQuestion())
            && !updateFrequentlyAskedQuestionInput.getQuestion().isBlank()) {
            frequentlyAskedQuestion.setQuestion(updateFrequentlyAskedQuestionInput.getQuestion());
        }

        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Override
    public Boolean deleteById (Long id) {
        frequentlyAskedQuestionRepository.deleteById(id);
        return true;
    }
}
