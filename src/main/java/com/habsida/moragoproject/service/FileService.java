package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.File;

import java.util.List;

public interface FileService {
    List<File> getAll();
    File getById(Long id);
    File create(File file);
    File update(File file);
    void deleteById(Long id);
}
