package com.habsida.moragoproject.service;

import com.habsida.moragoproject.MoragoProjectApplication;
import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.input.File64CreateInput;
import com.habsida.moragoproject.model.input.FileCreateInput;
import com.habsida.moragoproject.model.input.FileUpdateInput;
import com.habsida.moragoproject.repository.FileRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
        File file = fileRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("File is not found by id - " + id));
        try {
            Files.delete(Paths.get(file.getPath()));
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        fileRepository.deleteById(id);
        return true;
    }

    @Override
    public File createFileBase64(File64CreateInput file64CreateInput) {
        byte[] data = DatatypeConverter.parseBase64Binary(file64CreateInput.getBase64());

        java.io.File iconDirectory = new java.io.File("./icon");

        if (!iconDirectory.exists()) {
            iconDirectory.mkdirs();
        }

        java.io.File icon = new java.io.File(iconDirectory, file64CreateInput.getOriginalTitle());

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(icon))) {
            outputStream.write(data);
            File file = new File();

            file.setOriginalTitle(file64CreateInput.getOriginalTitle());
            file.setType(file64CreateInput.getType());
            file.setPath(iconDirectory + "/" + file64CreateInput.getOriginalTitle());

            return fileRepository.save(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String getFileBase64(File file) {
        java.io.File icon = new java.io.File(file.getPath());

        try (FileInputStream fileInputStream = new FileInputStream(icon)){

            byte[] data = new byte[(int) icon.length()];
            fileInputStream.read(data);

            return DatatypeConverter.printBase64Binary(data);

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String uploadFileByRestController(MultipartFile multipartFile) {
        byte[] data ;
        try {
            data = multipartFile.getBytes();
        } catch (IOException ex) {
            return ex.getMessage();
        }
        java.io.File iconDirectory = new java.io.File("./icon");
        if (!iconDirectory.exists()) {
            iconDirectory.mkdirs();
        }

        java.io.File icon = new java.io.File(iconDirectory, multipartFile.getOriginalFilename());

        try (OutputStream outputStream = new BufferedOutputStream (new FileOutputStream(icon))) {
            outputStream.write(data);

            File file = new File();
            file.setOriginalTitle(multipartFile.getOriginalFilename());
            file.setType(multipartFile.getContentType());
            file.setPath(iconDirectory + "/" + multipartFile.getOriginalFilename());
            fileRepository.save(file);

        } catch (IOException ex) {
            return ex.getMessage();
        }

        return "file uploaded successfully: " + multipartFile.getOriginalFilename();
    }

    @Override
    public byte[] downloadFileByRestController(String fileName) {
        Optional<File> file = fileRepository.findByOriginalTitle(fileName);
        if (!file.isPresent()) {
            return null;
        } else {
            java.io.File icon = new java.io.File(file.get().getPath());
            try (FileInputStream fileInputStream = new FileInputStream(icon)) {
                byte[] data = new byte[(int) icon.length()];
                fileInputStream.read(data);
                return data;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
