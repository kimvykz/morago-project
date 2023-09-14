package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateFileInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.UpdateFileInput;
import com.habsida.moragoproject.model.entity.File;
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
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
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
    public Page<File> getAllPaged (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return fileService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createFile")
    public File create (@Valid @Argument(name = "fileInput") CreateFileInput createFileInput) {

        return fileService.create(createFileInput);
    }

    @MutationMapping(name = "updateFile")
    public File update (@Valid @Argument(name = "fileInput") UpdateFileInput updateFileInput) {

        return fileService.update(updateFileInput);
    }

    @MutationMapping(name = "deleteFileById")
    public Boolean deleteById (@Argument Long id) {
        return fileService.deleteById(id);
    }


}