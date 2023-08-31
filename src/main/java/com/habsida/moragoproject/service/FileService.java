package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.File;

import java.util.List;

public interface FileService {
    List<File> getAllItems();
    File getItemById(Long id);
    File createItem(File file);
    File modifyItem(File file);
    void removeItem(Long id);
}
