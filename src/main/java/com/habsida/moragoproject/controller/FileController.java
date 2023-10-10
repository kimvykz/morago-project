package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.FileCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.FileUpdateInput;
import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Controller
public class FileController {

    private FileService fileService;

    public FileController (FileService fileService) {
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

    @QueryMapping(name = "getFilesByPaging")
    public Page<File> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return fileService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createFile")
    public File create (@Valid @Argument(name = "fileInput") FileCreateInput fileCreateInput) {

        return fileService.create(fileCreateInput);
    }

    @MutationMapping(name = "updateFile")
    public File update (@Valid @Argument(name = "fileInput") FileUpdateInput fileUpdateInput) {

        return fileService.update(fileUpdateInput);
    }

    @MutationMapping(name = "deleteFileById")
    public Boolean deleteById (@Argument Long id) {
        return fileService.deleteById(id);
    }

    @MutationMapping(name = "uploadFile")
    public File uploadFile (@Argument(name = "fileUploadInput") MultipartFile file) {
        return fileService.uploadFile(file);
    }

}
