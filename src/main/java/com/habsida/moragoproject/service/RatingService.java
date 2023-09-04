package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RatingService {
    List<Rating> getAll ();
    Page<Rating> getAllPaged(PageRequest pageRequest);
    Rating getById (Long id);
    Rating create (Rating rating);
    Rating update (Rating rating);
    Boolean deleteById (Long id);
}
