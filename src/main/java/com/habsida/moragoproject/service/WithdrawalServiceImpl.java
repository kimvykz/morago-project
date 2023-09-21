package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.input.WithdrawalCreateInput;
import com.habsida.moragoproject.model.input.WithdrawalUpdateInput;
import com.habsida.moragoproject.repository.WithdrawalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<Withdrawal> getAllByPaging (PageRequest pageRequest) {
        return withdrawalRepository.findAll(pageRequest);
    }

    @Override
    public Withdrawal getById (Long id) {
        return withdrawalRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Withdrawal is not found for the id - " + id);
        });
    }

    @Override
    public Withdrawal create (WithdrawalCreateInput withdrawalCreateInput) {
        Withdrawal withdrawal = new Withdrawal();

        if (withdrawalCreateInput.getAccountHolder() == null
            || withdrawalCreateInput.getAccountHolder().isBlank()) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        } else {
            withdrawal.setAccountHolder(withdrawalCreateInput.getAccountHolder());
        }
        if (withdrawalCreateInput.getAccountNumber() == null
            || withdrawalCreateInput.getAccountHolder().isBlank()) {
            throw new IllegalArgumentException("field accountNumber cannot be null");
        } else {
            withdrawal.setAccountNumber(withdrawalCreateInput.getAccountNumber());
        }
        if (withdrawalCreateInput.getNameOfBank() == null
            || withdrawalCreateInput.getNameOfBank().isBlank()) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        } else {
            withdrawal.setNameOfBank(withdrawalCreateInput.getNameOfBank());
        }
        if (withdrawalCreateInput.getStatus() == null) {
            throw new IllegalArgumentException("field status cannot be null");
        } else {
            withdrawal.setStatus(withdrawalCreateInput.getStatus());
        }
        if (withdrawalCreateInput.getSum() == null) {
            throw new IllegalArgumentException("field sum cannot be null");
        } else {
            withdrawal.setSum(withdrawalCreateInput.getSum());
        }
        if (withdrawalCreateInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            withdrawal.setUser(withdrawalCreateInput.getUser());
        }

        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal update(WithdrawalUpdateInput withdrawalUpdateInput) {
        Withdrawal withdrawal = getById(withdrawalUpdateInput.getId());

        if (withdrawalUpdateInput.getAccountHolder() != null
            && !withdrawal.getAccountHolder().equals(withdrawalUpdateInput.getAccountHolder())
            && !withdrawalUpdateInput.getAccountHolder().isBlank()) {
            withdrawal.setAccountHolder(withdrawalUpdateInput.getAccountHolder());
        }
        if (withdrawalUpdateInput.getAccountNumber() != null
            && !withdrawalUpdateInput.getAccountNumber().isBlank()
            && !withdrawal.getAccountNumber().equals(withdrawalUpdateInput.getAccountNumber())) {
            withdrawal.setAccountNumber(withdrawalUpdateInput.getAccountNumber());
        }
        if (withdrawalUpdateInput.getNameOfBank() != null
            && !withdrawalUpdateInput.getNameOfBank().isBlank()
            && !withdrawal.getNameOfBank().equals(withdrawalUpdateInput.getNameOfBank())) {
            withdrawal.setNameOfBank(withdrawalUpdateInput.getNameOfBank());
        }
        if (withdrawalUpdateInput.getStatus() != null
            && !withdrawal.getStatus().equals(withdrawalUpdateInput.getStatus())) {
            withdrawal.setStatus(withdrawalUpdateInput.getStatus());
        }
        if (withdrawalUpdateInput.getSum() != null
            && !withdrawal.getSum().equals(withdrawalUpdateInput.getSum())) {
            withdrawal.setSum(withdrawalUpdateInput.getSum());
        }
        if (withdrawalUpdateInput.getUser() != null
            && !withdrawal.getUser().equals(withdrawalUpdateInput.getUser())) {
            withdrawal.setUser(withdrawalUpdateInput.getUser());
        }

        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Boolean deleteById (Long id) {
        withdrawalRepository.deleteById(id);
        return true;
    }
}
