package com.habsida.moragoproject.service;


import com.habsida.moragoproject.model.entity.Call;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CallService {
    List<Call> getAll();
    Page<Call> getAllPaged(PageRequest pageRequest);
    Call getById(Long id);
    Call create(Call call);
    Call update(Call call);
    Boolean deleteById(Long id);
}
