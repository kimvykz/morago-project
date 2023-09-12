package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.input.CreateFileInput;
import com.habsida.moragoproject.model.input.UpdateFileInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface FileService {
    List<File> getAll();
    Page<File> getAllPaged(PageRequest pageRequest);
    File getById(Long id);
    File create(CreateFileInput createFileInput);
    File update(UpdateFileInput updateFileInput);
    Boolean deleteById(Long id);
}
