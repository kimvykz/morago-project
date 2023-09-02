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
    public List<File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public File getById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("File is not found for the id - " + id);
        });
    }

    @Override
    public File create(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File update(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }
}
