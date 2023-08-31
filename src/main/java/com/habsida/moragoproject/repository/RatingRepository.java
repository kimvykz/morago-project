package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
