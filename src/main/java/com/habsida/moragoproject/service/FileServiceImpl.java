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

    public FileServiceImpl (FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<File> getAll () {
        return fileRepository.findAll();
    }

    @Override
    public Page<File> getAllByPaging (PageRequest pageRequest) {
        return fileRepository.findAll(pageRequest);
    }

    @Override
    public File getById (Long id) {
        return fileRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("File is not found for the id - " + id);
        });
    }

    @Override
    public File create (CreateFileInput createFileInput) {
        File file = new File();

        if (createFileInput.getOriginalTitle() == null
            || createFileInput.getOriginalTitle().isBlank()) {
            throw new IllegalArgumentException("field originalTitle cannot be null");
        } else {
            file.setOriginalTitle(createFileInput.getOriginalTitle());
        }
        if (createFileInput.getPath() == null
            || createFileInput.getPath().isBlank()) {
            throw new IllegalArgumentException("field path cannot be null");
        } else {
            file.setPath(createFileInput.getPath());
        }
        if (createFileInput.getType() == null
            || createFileInput.getType().isBlank()) {
            throw new IllegalArgumentException("field type cannot be null");
        } else {
            file.setType(createFileInput.getType());
        }
        if (createFileInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            file.setUser(createFileInput.getUser());
        }
        if (createFileInput.getTheme() == null) {
            throw new IllegalArgumentException("field theme cannot be null");
        } else {
            file.setTheme(createFileInput.getTheme());
        }

        return fileRepository.save(file);
    }

    @Override
    public File update (UpdateFileInput updateFileInput) {
        File file = getById(updateFileInput.getId());

        if (updateFileInput.getOriginalTitle() != null
            && !updateFileInput.getOriginalTitle().isBlank()
            && !file.getOriginalTitle().equals(updateFileInput.getOriginalTitle())) {
            file.setOriginalTitle(updateFileInput.getOriginalTitle());
        }
        if (updateFileInput.getPath() != null
            && !file.getPath().equals(updateFileInput.getPath())
            && !updateFileInput.getPath().isBlank()) {
            file.setPath(updateFileInput.getPath());
        }
        if (updateFileInput.getType() != null
            && !updateFileInput.getType().isBlank()
            && !file.getType().equals(updateFileInput.getType())) {
            file.setType(updateFileInput.getType());
        }
        if (updateFileInput.getUser() != null
            && !file.getUser().equals(updateFileInput.getUser())) {
            file.setUser(updateFileInput.getUser());
        }
        if (updateFileInput.getTheme() != null
            && !file.getTheme().equals(updateFileInput.getTheme())) {
            file.setTheme(updateFileInput.getTheme());
        }

        return fileRepository.save(file);
    }

    @Override
    public Boolean deleteById (Long id) {
        fileRepository.deleteById(id);
        return true;
    }
}
