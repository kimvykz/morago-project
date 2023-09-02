package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> getAll ();
    Rating getById (Long id);
    Rating create (Rating rating);
    Rating update (Rating rating);
    void deleteById (Long id);
}
