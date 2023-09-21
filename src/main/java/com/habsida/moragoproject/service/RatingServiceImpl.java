package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.input.RatingCreateInput;
import com.habsida.moragoproject.model.input.RatingUpdateInput;
import com.habsida.moragoproject.repository.RatingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;

    public RatingServiceImpl (RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getAll () {
        return ratingRepository.findAll();
    }

    @Override
    public Page<Rating> getAllByPaging (PageRequest pageRequest) {
        return ratingRepository.findAll(pageRequest);
    }

    @Override
    public Rating getById (Long id) {
        return ratingRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Rating is not fount for the id - " + id);
        });
    }

    @Override
    public Rating create (RatingCreateInput ratingCreateInput) {
        Rating rating = new Rating();

        if (ratingCreateInput.getGrade() == null) {
            throw new IllegalArgumentException("field grade cannot be null");
        } else {
            rating.setGrade(ratingCreateInput.getGrade());
        }
        if (ratingCreateInput.getRaterUser() == null) {
            throw new IllegalArgumentException("field raterUser cannot be null");
        } else {
            rating.setRaterUser(ratingCreateInput.getRaterUser());
        }
        if (ratingCreateInput.getAssessedUser() == null) {
            throw new IllegalArgumentException("field raterUser cannot be null");
        } else {
            rating.setAssessedUser(ratingCreateInput.getAssessedUser());
        }
        return ratingRepository.save(rating);
    }

    @Override
    public Rating update (RatingUpdateInput ratingUpdateInput) {
        Rating rating = getById(ratingUpdateInput.getId());

        if (ratingUpdateInput.getGrade() != null
            && !rating.getGrade().equals(ratingUpdateInput.getGrade())) {
            rating.setGrade(ratingUpdateInput.getGrade());
        }
        if (ratingUpdateInput.getRaterUser() != null
                && !rating.getRaterUser().equals(ratingUpdateInput.getRaterUser())) {
            rating.setRaterUser(ratingUpdateInput.getRaterUser());
        }
        if (ratingUpdateInput.getAssessedUser() != null
                && !rating.getAssessedUser().equals(ratingUpdateInput.getAssessedUser())) {
            rating.setAssessedUser(ratingUpdateInput.getAssessedUser());
        }
        return ratingRepository.save(rating);
    }

    @Override
    public Boolean deleteById (Long id) {
        ratingRepository.deleteById(id);
        return true;
    }
}
