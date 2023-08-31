package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.File;
import com.habsida.moragoproject.repository.FileRepository;

import java.util.List;
import java.util.Optional;

public class FileServiceImpl implements FileService{

    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<File> getAllItems() {
        return fileRepository.findAll();
    }

    @Override
    public File getItemById(Long id) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isPresent()){
            file.get();
        }
        throw new RuntimeException("File is not found for the id - " + id);
    }

    @Override
    public File createItem(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File modifyItem(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void removeItem(Long id) {
        fileRepository.deleteById(id);
    }
}
