package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Deposit;
import com.habsida.moragoproject.repository.DepositRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void deleteById(Long id) {
        depositRepository.deleteById(id);
    }
}
