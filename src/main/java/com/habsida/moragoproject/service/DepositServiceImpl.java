package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.repository.DepositRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService{

    private DepositRepository depositRepository;

    public DepositServiceImpl(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public List<Deposit> getAll() {
        return depositRepository.findAll();
    }

    @Override
    public Page<Deposit> getAllPaged(PageRequest pageRequest) {
        return depositRepository.findAll(pageRequest);
    }

    @Override
    public Deposit getById(Long id) {
        return depositRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Deposit is not found for the id - " + id);
        });
    }

    @Override
    public Deposit create(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public Deposit update(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public Boolean deleteById(Long id) {
        depositRepository.deleteById(id);
        return true;
    }
}
