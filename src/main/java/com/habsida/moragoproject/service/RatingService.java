package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.input.RatingCreateInput;
import com.habsida.moragoproject.model.input.RatingUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RatingService {
    List<Rating> getAll ();
    Page<Rating> getAllByPaging (PageRequest pageRequest);
    Rating getById (Long id);
    Rating create (RatingCreateInput ratingCreateInput);
    Rating update (RatingUpdateInput ratingUpdateInput);
    Boolean deleteById (Long id);
}
