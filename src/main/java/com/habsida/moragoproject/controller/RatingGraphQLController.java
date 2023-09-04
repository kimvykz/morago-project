package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.RatingDTO;
import com.habsida.moragoproject.entity.Rating;
import com.habsida.moragoproject.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class RatingGraphQLController {
    private ModelMapper modelMapper;

    private RatingService ratingService;

    public RatingGraphQLController(ModelMapper modelMapper,
                                   RatingService ratingService) {
        this.modelMapper = modelMapper;
        this.ratingService = ratingService;
    }

    @QueryMapping(name = "getRatings")
    public Iterable<Rating> getAll (){
        return ratingService.getAll();
    }

    @QueryMapping(name = "getRatingById")
    public Rating getById (@Argument Long id) {
        return ratingService.getById(id);
    }

    @QueryMapping(name = "getRatingsPaged")
    public Page<Rating> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ratingService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createRating")
    public Rating create (@Valid @Argument(name = "rating") RatingDTO ratingDTO) {
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        return ratingService.create(rating);
    }

    @MutationMapping(name = "updateRatingById")
    public Rating updateById (@Valid @Argument Long id,
                              @Argument(name = "rating") RatingDTO ratingDTO) {
        Rating rating = ratingService.getById(id);
        modelMapper.map(ratingDTO, rating);
        return ratingService.update(rating);
    }

    @MutationMapping(name = "deleteRatingById")
    public Boolean deleteById (@Argument Long id) {
        return ratingService.deleteById(id);
    }


}
