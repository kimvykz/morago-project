package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Rating;
import com.habsida.moragoproject.repository.RatingRepository;

import java.util.List;
import java.util.Optional;

public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getAllItems() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getItemById(Long id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()){
            return rating.get();
        }
        throw new RuntimeException("Rating is not fount for the id - " + id);
    }

    @Override
    public Rating createItem(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating modifyItem(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void removeItem(Long id) {
        ratingRepository.deleteById(id);
    }
}
