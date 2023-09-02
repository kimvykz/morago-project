package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.repository.FrequentlyAskedQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrequentlyAskedQuestionServiceImpl implements FrequentlyAskedQuestionService{
    private FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;

    public FrequentlyAskedQuestionServiceImpl(FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository) {
        this.frequentlyAskedQuestionRepository = frequentlyAskedQuestionRepository;
    }

    @Override
    public List<FrequentlyAskedQuestion> getAll() {
        return frequentlyAskedQuestionRepository.findAll();
    }

    @Override
    public FrequentlyAskedQuestion getById(Long id) {
        return frequentlyAskedQuestionRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("FrequentlyAskedQuestion is not found for the id - " + id);
        });
    }

    @Override
    public FrequentlyAskedQuestion create(FrequentlyAskedQuestion frequentlyAskedQuestion) {
        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Override
    public FrequentlyAskedQuestion update(FrequentlyAskedQuestion frequentlyAskedQuestion) {
        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Override
    public void deleteById(Long id) {
        frequentlyAskedQuestionRepository.deleteById(id);
    }
}
