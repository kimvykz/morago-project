package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Withdrawal;
import com.habsida.moragoproject.repository.WithdrawalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{
    private WithdrawalRepository withdrawalRepository;

    public WithdrawalServiceImpl (WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    @Override
    public List<Withdrawal> getAll () {
        return withdrawalRepository.findAll();
    }

    @Override
    public Withdrawal getById (Long id) {
        return withdrawalRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Withdrawal is not found for the id - " + id);
        });
    }

    @Override
    public Withdrawal create (Withdrawal withdrawal) {
        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal update(Withdrawal withdrawal) {
        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public void deleteById(Long id) {
        withdrawalRepository.deleteById(id);
    }
}
