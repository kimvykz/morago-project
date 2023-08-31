package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> getAllItems();
    Rating getItemById(Long id);
    Rating createItem(Rating rating);
    Rating modifyItem(Rating rating);
    void removeItem(Long id);
}
