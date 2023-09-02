package com.habsida.moragoproject.service;


import com.habsida.moragoproject.entity.Call;

import java.util.List;

public interface CallService {
    List<Call> getAll();
    Call getById(Long id);
    Call create(Call call);
    Call update(Call call);
    void deleteById(Long id);
}
