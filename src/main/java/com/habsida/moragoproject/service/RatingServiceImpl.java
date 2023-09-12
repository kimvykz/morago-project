package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateRatingInput;
import com.habsida.moragoproject.model.input.UpdateRatingInput;
import com.habsida.moragoproject.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;
    private ModelMapper modelMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
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
    public Rating create (CreateRatingInput createRatingInput) {
        Rating rating = modelMapper.map(createRatingInput, Rating.class);
        if (rating.getGrade() == null) {
            throw new IllegalArgumentException("field grade cannot be null");
        }
        if (rating.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }
        return ratingRepository.save(rating);
    }

    @Override
    public Rating update (UpdateRatingInput updateRatingInput) {
        Rating rating = getById(updateRatingInput.getId());
        modelMapper.map(updateRatingInput, rating);
        if (rating.getGrade() == null) {
            throw new IllegalArgumentException("field grade cannot be null");
        }
        if (rating.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }
        return ratingRepository.save(rating);
    }

    @Override
    public Boolean deleteById (Long id) {
        ratingRepository.deleteById(id);
        return true;
    }
}
