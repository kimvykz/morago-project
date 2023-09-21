package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.RatingCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.RatingUpdateInput;
import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.service.RatingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class RatingController {

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
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

    @QueryMapping(name = "getRatingsByPaging")
    public Page<Rating> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return ratingService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createRating")
    public Rating create (@Valid @Argument(name = "ratingInput") RatingCreateInput ratingCreateInput) {
        return ratingService.create(ratingCreateInput);
    }

    @MutationMapping(name = "updateRating")
    public Rating update (@Valid @Argument(name = "ratingInput") RatingUpdateInput ratingUpdateInput) {
        return ratingService.update(ratingUpdateInput);
    }

    @MutationMapping(name = "deleteRatingById")
    public Boolean deleteById (@Argument Long id) {
        return ratingService.deleteById(id);
    }


}
