package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.input.FileCreateInput;
import com.habsida.moragoproject.model.input.FileUpdateInput;
import com.habsida.moragoproject.repository.FileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public File create (FileCreateInput fileCreateInput) {
        File file = new File();

        if (fileCreateInput.getOriginalTitle() == null
            || fileCreateInput.getOriginalTitle().isBlank()) {
            throw new IllegalArgumentException("field originalTitle cannot be null");
        } else {
            file.setOriginalTitle(fileCreateInput.getOriginalTitle());
        }
        if (fileCreateInput.getPath() == null
            || fileCreateInput.getPath().isBlank()) {
            throw new IllegalArgumentException("field path cannot be null");
        } else {
            file.setPath(fileCreateInput.getPath());
        }
        if (fileCreateInput.getType() == null
            || fileCreateInput.getType().isBlank()) {
            throw new IllegalArgumentException("field type cannot be null");
        } else {
            file.setType(fileCreateInput.getType());
        }

        return fileRepository.save(file);
    }

    @Override
    public File update (FileUpdateInput fileUpdateInput) {
        File file = getById(fileUpdateInput.getId());

        if (fileUpdateInput.getOriginalTitle() != null
            && !fileUpdateInput.getOriginalTitle().isBlank()
            && !file.getOriginalTitle().equals(fileUpdateInput.getOriginalTitle())) {
            file.setOriginalTitle(fileUpdateInput.getOriginalTitle());
        }
        if (fileUpdateInput.getPath() != null
            && !file.getPath().equals(fileUpdateInput.getPath())
            && !fileUpdateInput.getPath().isBlank()) {
            file.setPath(fileUpdateInput.getPath());
        }
        if (fileUpdateInput.getType() != null
            && !fileUpdateInput.getType().isBlank()
            && !file.getType().equals(fileUpdateInput.getType())) {
            file.setType(fileUpdateInput.getType());
        }

        return fileRepository.save(file);
    }

    @Override
    public Boolean deleteById (Long id) {
        fileRepository.deleteById(id);
        return true;
    }
}
