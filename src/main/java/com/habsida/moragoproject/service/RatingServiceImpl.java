package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.repository.RatingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<Rating> getAllPaged(PageRequest pageRequest) {
        return ratingRepository.findAll(pageRequest);
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
    public Boolean deleteById (Long id) {
        ratingRepository.deleteById(id);
        return true;
    }
}
