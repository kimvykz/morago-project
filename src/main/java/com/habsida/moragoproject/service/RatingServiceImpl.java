package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Rating;
import com.habsida.moragoproject.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getAll () {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getById (Long id) {
        return ratingRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Rating is not fount for the id - " + id);
        });
    }

    @Override
    public Rating create (Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating update (Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteById (Long id) {
        ratingRepository.deleteById(id);
    }
}
