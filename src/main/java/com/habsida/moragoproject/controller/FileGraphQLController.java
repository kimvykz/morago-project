package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.FileDTO;
import com.habsida.moragoproject.entity.File;
import com.habsida.moragoproject.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class FileGraphQLController {
    private ModelMapper modelMapper;

    private FileService fileService;

    public FileGraphQLController(ModelMapper modelMapper,
                                 FileService fileService) {
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    @QueryMapping(name = "getFiles")
    public Iterable<File> getAll (){
        return fileService.getAll();
    }

    @QueryMapping(name = "getFileById")
    public File getById (@Argument Long id) {
        return fileService.getById(id);
    }

    @QueryMapping(name = "getFilesPaged")
    public Page<File> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return fileService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createFile")
    public File create (@Valid @Argument(name = "file") FileDTO fileDTO) {
        File file = modelMapper.map(fileDTO, File.class);
        return fileService.create(file);
    }

    @MutationMapping(name = "updateFileById")
    public File updateById (@Valid @Argument Long id,
                              @Argument(name = "file") FileDTO fileDTO) {
        File file = fileService.getById(id);
        modelMapper.map(fileDTO, file);
        return fileService.update(file);
    }

    @MutationMapping(name = "deleteFileById")
    public Boolean deleteById (@Argument Long id) {
        return fileService.deleteById(id);
    }


}
