package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Call;
import com.habsida.moragoproject.repository.CallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallServiceImpl implements CallService{

    private CallRepository callRepository;

    public CallServiceImpl(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @Override
    public List<Call> getAll() {
        return callRepository.findAll();
    }

    @Override
    public Call getById(Long id) {
        return callRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Call is not found for the id - " + id);
        });
    }

    @Override
    public Call create(Call call) {
        return callRepository.save(call);
    }

    @Override
    public Call update(Call call) {
        return callRepository.save(call);
    }

    @Override
    public void deleteById(Long id) {
        callRepository.deleteById(id);
    }
}
