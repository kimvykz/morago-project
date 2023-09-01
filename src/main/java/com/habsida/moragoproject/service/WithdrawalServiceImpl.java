package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Withdrawal;
import com.habsida.moragoproject.repository.WithdrawalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{
    private WithdrawalRepository withdrawalRepository;

    public WithdrawalServiceImpl(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    @Override
    public List<Withdrawal> getAllItems() {
        return withdrawalRepository.findAll();
    }

    @Override
    public Withdrawal getItemById(Long id) {
        Optional<Withdrawal> withdrawal = withdrawalRepository.findById(id);
        if (withdrawal.isPresent()){
            return withdrawal.get();
        }
        throw new RuntimeException("Withdrawal is not found for the id - " + id);
    }

    @Override
    public Withdrawal createItem(Withdrawal withdrawal) {
        return null;
    }

    @Override
    public Withdrawal modifyItem(Withdrawal withdrawal) {
        return null;
    }

    @Override
    public void removeItem(Long id) {

    }
}
