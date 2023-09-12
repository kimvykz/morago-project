package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.input.CreateRatingInput;
import com.habsida.moragoproject.model.input.UpdateRatingInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RatingService {
    List<Rating> getAll ();
    Page<Rating> getAllPaged(PageRequest pageRequest);
    Rating getById (Long id);
    Rating create (CreateRatingInput createRatingInput);
    Rating update (UpdateRatingInput updateRatingInput);
    Boolean deleteById (Long id);
}
