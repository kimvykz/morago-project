package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateFileInput;
import com.habsida.moragoproject.model.input.UpdateFileInput;
import com.habsida.moragoproject.repository.FileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    private FileRepository fileRepository;
    private ModelMapper modelMapper;

    public FileServiceImpl(FileRepository fileRepository, ModelMapper modelMapper) {
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public Page<File> getAllPaged(PageRequest pageRequest) {
        return fileRepository.findAll(pageRequest);
    }

    @Override
    public File getById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("File is not found for the id - " + id);
        });
    }

    @Override
    public File create(CreateFileInput createFileInput) {
        File file = modelMapper.map(createFileInput, File.class);
        if (file.getOriginalTitle() == null) {
            throw new IllegalArgumentException("field originalTitle cannot be null");
        }
        if (file.getPath() == null) {
            throw new IllegalArgumentException("field path cannot be null");
        }
        if (file.getType() == null) {
            throw new IllegalArgumentException("field type cannot be null");
        }
        if (file.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }
        if (file.getTheme() == null) {
            throw new IllegalArgumentException("field theme cannot be null");
        }

        return fileRepository.save(file);
    }

    @Override
    public File update(UpdateFileInput updateFileInput) {
        File file = getById(updateFileInput.getId());
        modelMapper.map(updateFileInput, file);

        if (file.getOriginalTitle() == null) {
            throw new IllegalArgumentException("field originalTitle cannot be null");
        }
        if (file.getPath() == null) {
            throw new IllegalArgumentException("field path cannot be null");
        }
        if (file.getType() == null) {
            throw new IllegalArgumentException("field type cannot be null");
        }
        if (file.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }
        if (file.getTheme() == null) {
            throw new IllegalArgumentException("field theme cannot be null");
        }

        return fileRepository.save(file);
    }

    @Override
    public Boolean deleteById(Long id) {
        fileRepository.deleteById(id);
        return true;
    }
}
