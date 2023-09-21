package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.FrequentlyAskedQuestionCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.FrequentlyAskedQuestionUpdateInput;
import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.service.FrequentlyAskedQuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class FrequentlyAskedQuestionController {

    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;

    public FrequentlyAskedQuestionController (FrequentlyAskedQuestionService frequentlyAskedQuestionService) {
        this.frequentlyAskedQuestionService = frequentlyAskedQuestionService;
    }

    @QueryMapping(name = "getFrequentlyAskedQuestions")
    public Iterable<FrequentlyAskedQuestion> getAll (){
        return frequentlyAskedQuestionService.getAll();
    }

    @QueryMapping(name = "getFrequentlyAskedQuestionById")
    public FrequentlyAskedQuestion getById (@Argument Long id) {
        return frequentlyAskedQuestionService.getById(id);
    }

    @QueryMapping(name = "getFrequentlyAskedQuestionsByPaging")
    public Page<FrequentlyAskedQuestion> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return frequentlyAskedQuestionService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createFrequentlyAskedQuestion")
    public FrequentlyAskedQuestion create (@Valid
        @Argument(name = "frequentlyAskedQuestionInput") FrequentlyAskedQuestionCreateInput frequentlyAskedQuestionCreateInput) {
        return frequentlyAskedQuestionService.create(frequentlyAskedQuestionCreateInput);
    }

    @MutationMapping(name = "updateFrequentlyAskedQuestion")
    public FrequentlyAskedQuestion update (@Valid
        @Argument(name = "frequentlyAskedQuestionInput") FrequentlyAskedQuestionUpdateInput frequentlyAskedQuestionUpdateInput) {
        return frequentlyAskedQuestionService.update(frequentlyAskedQuestionUpdateInput);
    }

    @MutationMapping(name = "deleteFrequentlyAskedQuestionById")
    public Boolean deleteById (@Argument Long id) {
        return frequentlyAskedQuestionService.deleteById(id);
    }


}
