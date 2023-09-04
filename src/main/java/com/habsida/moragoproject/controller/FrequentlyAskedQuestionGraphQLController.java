package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.FrequentlyAskedQuestionDTO;
import com.habsida.moragoproject.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.service.FrequentlyAskedQuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class FrequentlyAskedQuestionGraphQLController {
    private ModelMapper modelMapper;

    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;

    public FrequentlyAskedQuestionGraphQLController(ModelMapper modelMapper,
                                                    FrequentlyAskedQuestionService frequentlyAskedQuestionService) {
        this.modelMapper = modelMapper;
        this.frequentlyAskedQuestionService = frequentlyAskedQuestionService;
    }

    @QueryMapping(name = "getFrequentlyAskedQuestion")
    public Iterable<FrequentlyAskedQuestion> getAll (){
        return frequentlyAskedQuestionService.getAll();
    }

    @QueryMapping(name = "getFrequentlyAskedQuestionById")
    public FrequentlyAskedQuestion getById (@Argument Long id) {
        return frequentlyAskedQuestionService.getById(id);
    }

    @QueryMapping(name = "getFrequentlyAskedQuestionsPaged")
    public Page<FrequentlyAskedQuestion> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return frequentlyAskedQuestionService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createFrequentlyAskedQuestion")
    public FrequentlyAskedQuestion create (@Valid @Argument(name = "frequentlyAskedQuestion") FrequentlyAskedQuestionDTO frequentlyAskedQuestionDTO) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = modelMapper.map(frequentlyAskedQuestionDTO, FrequentlyAskedQuestion.class);
        return frequentlyAskedQuestionService.create(frequentlyAskedQuestion);
    }

    @MutationMapping(name = "updateFrequentlyAskedQuestionById")
    public FrequentlyAskedQuestion updateById (@Valid @Argument Long id,
                              @Argument(name = "frequentlyAskedQuestion") FrequentlyAskedQuestionDTO frequentlyAskedQuestionDTO) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = frequentlyAskedQuestionService.getById(id);
        modelMapper.map(frequentlyAskedQuestionDTO, frequentlyAskedQuestion);
        return frequentlyAskedQuestionService.update(frequentlyAskedQuestion);
    }

    @MutationMapping(name = "deleteFrequentlyAskedQuestionById")
    public Boolean deleteById (@Argument Long id) {
        return frequentlyAskedQuestionService.deleteById(id);
    }


}
