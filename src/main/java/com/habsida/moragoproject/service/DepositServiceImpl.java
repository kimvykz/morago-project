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
    public List<Deposit> getAllItems() {
        return depositRepository.findAll();
    }

    @Override
    public Deposit getItemById(Long id) {
        Optional<Deposit> deposit = depositRepository.findById(id);
        if (deposit.isPresent()) {
            return deposit.get();
        }
        throw new RuntimeException("Deposit is not found for the id - " + id);
    }

    @Override
    public Deposit createItem(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public Deposit modifyItem(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public void removeItem(Long id) {
        depositRepository.deleteById(id);
    }
}
