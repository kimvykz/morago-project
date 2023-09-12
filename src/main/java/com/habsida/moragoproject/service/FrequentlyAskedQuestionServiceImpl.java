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
    private ModelMapper modelMapper;

    public FrequentlyAskedQuestionServiceImpl(FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository,
                                              ModelMapper modelMapper) {
        this.frequentlyAskedQuestionRepository = frequentlyAskedQuestionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FrequentlyAskedQuestion> getAll() {
        return frequentlyAskedQuestionRepository.findAll();
    }

    @Override
    public Page<FrequentlyAskedQuestion> getAllPaged(PageRequest pageRequest) {
        return frequentlyAskedQuestionRepository.findAll(pageRequest);
    }

    @Override
    public FrequentlyAskedQuestion getById(Long id) {
        return frequentlyAskedQuestionRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("FrequentlyAskedQuestion is not found for the id - " + id);
        });
    }

    @Override
    public FrequentlyAskedQuestion create(CreateFrequentlyAskedQuestionInput createFrequentlyAskedQuestionInput) {
        FrequentlyAskedQuestion frequentlyAskedQuestion =
                modelMapper.map(createFrequentlyAskedQuestionInput, FrequentlyAskedQuestion.class);
        if (frequentlyAskedQuestion.getAnswer() == null) {
            throw new IllegalArgumentException("field answer cannot be null");
        }
        if (frequentlyAskedQuestion.getCategory() == null) {
            throw new IllegalArgumentException("field category cannot be null");
        }
        if (frequentlyAskedQuestion.getQuestion() == null) {
            throw new IllegalArgumentException("field question cannot be null");
        }

        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Override
    public FrequentlyAskedQuestion update(UpdateFrequentlyAskedQuestionInput updateFrequentlyAskedQuestionInput) {
        FrequentlyAskedQuestion frequentlyAskedQuestion =
                getById(updateFrequentlyAskedQuestionInput.getId());
        modelMapper.map(updateFrequentlyAskedQuestionInput, frequentlyAskedQuestion);

        if (frequentlyAskedQuestion.getAnswer() == null) {
            throw new IllegalArgumentException("field answer cannot be null");
        }
        if (frequentlyAskedQuestion.getCategory() == null) {
            throw new IllegalArgumentException("field category cannot be null");
        }
        if (frequentlyAskedQuestion.getQuestion() == null) {
            throw new IllegalArgumentException("field question cannot be null");
        }

        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Override
    public Boolean deleteById(Long id) {
        frequentlyAskedQuestionRepository.deleteById(id);
        return true;
    }
}
