package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Call;
import com.habsida.moragoproject.repository.CallRepository;

import java.util.List;
import java.util.Optional;

public class CallServiceImpl implements CallService{

    private CallRepository callRepository;

    public CallServiceImpl(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @Override
    public List<Call> getAllItems() {
        return callRepository.findAll();
    }

    @Override
    public Call getItemById(Long id) {
        Optional<Call> call = callRepository.findById(id);
        if (call.isPresent()){
            return call.get();
        }
        throw new RuntimeException("Call is not found for the id - " + id);
    }

    @Override
    public Call createItem(Call call) {
        return callRepository.save(call);
    }

    @Override
    public Call modifyItem(Call call) {
        return callRepository.save(call);
    }

    @Override
    public void removeItem(Long id) {
        callRepository.deleteById(id);
    }
}
