package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.File;
import com.habsida.moragoproject.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        return fileRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("File is not found for the id - " + id);
        });
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
