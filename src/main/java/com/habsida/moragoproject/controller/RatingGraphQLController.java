package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateRatingInput;
import com.habsida.moragoproject.model.input.UpdateRatingInput;
import com.habsida.moragoproject.model.entity.Rating;
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
    public Rating create (@Valid @Argument(name = "rating") CreateRatingInput createRatingInput) {
        Rating rating = modelMapper.map(createRatingInput, Rating.class);
        return ratingService.create(rating);
    }

    @MutationMapping(name = "updateRatingById")
    public Rating updateById (@Valid @Argument Long id,
                              @Argument(name = "rating") UpdateRatingInput updateRatingInput) {
        Rating rating = ratingService.getById(id);
        modelMapper.map(updateRatingInput, rating);
        return ratingService.update(rating);
    }

    @MutationMapping(name = "deleteRatingById")
    public Boolean deleteById (@Argument Long id) {
        return ratingService.deleteById(id);
    }


}
