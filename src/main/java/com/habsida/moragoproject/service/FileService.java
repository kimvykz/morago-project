package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface FileService {
    List<File> getAll();
    Page<File> getAllPaged(PageRequest pageRequest);
    File getById(Long id);
    File create(File file);
    File update(File file);
    Boolean deleteById(Long id);
}
