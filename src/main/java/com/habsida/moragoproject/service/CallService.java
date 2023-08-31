package com.habsida.moragoproject.service;


import com.habsida.moragoproject.entity.Call;

import java.util.List;

public interface CallService {
    List<Call> getAllItems();
    Call getItemById(Long id);
    Call createItem(Call call);
    Call modifyItem(Call call);
    void removeItem(Long id);
}
