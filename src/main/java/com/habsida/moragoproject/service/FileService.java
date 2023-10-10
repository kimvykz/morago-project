package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.input.FileCreateInput;
import com.habsida.moragoproject.model.input.FileUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<File> getAll ();
    Page<File> getAllByPaging (PageRequest pageRequest);
    File getById (Long id);
    File create (FileCreateInput fileCreateInput);
    File update (FileUpdateInput fileUpdateInput);
    Boolean deleteById (Long id);
    File uploadFile(MultipartFile multipartFile);
    String uploadFileByRestController (MultipartFile file) throws IOException;
    byte[] downloadFileByRestController (String fileName);

}
