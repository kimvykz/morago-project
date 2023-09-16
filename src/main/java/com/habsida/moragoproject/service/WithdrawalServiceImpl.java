package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.enums.EStatus;
import com.habsida.moragoproject.model.input.CreateWithdrawalInput;
import com.habsida.moragoproject.model.input.UpdateWithdrawalInput;
import com.habsida.moragoproject.repository.WithdrawalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    public Withdrawal create (CreateWithdrawalInput createWithdrawalInput) {
        Withdrawal withdrawal = new Withdrawal();

        if (createWithdrawalInput.getAccountHolder() == null
            || createWithdrawalInput.getAccountHolder().isBlank()) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        } else {
            withdrawal.setAccountHolder(createWithdrawalInput.getAccountHolder());
        }
        if (createWithdrawalInput.getAccountNumber() == null
            || createWithdrawalInput.getAccountHolder().isBlank()) {
            throw new IllegalArgumentException("field accountNumber cannot be null");
        } else {
            withdrawal.setAccountNumber(createWithdrawalInput.getAccountNumber());
        }
        if (createWithdrawalInput.getNameOfBank() == null
            || createWithdrawalInput.getNameOfBank().isBlank()) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        } else {
            withdrawal.setNameOfBank(createWithdrawalInput.getNameOfBank());
        }
        if (createWithdrawalInput.getStatus() == null) {
            throw new IllegalArgumentException("field status cannot be null");
        } else {
            withdrawal.setStatus(createWithdrawalInput.getStatus());
        }
        if (createWithdrawalInput.getSum() == null) {
            throw new IllegalArgumentException("field sum cannot be null");
        } else {
            withdrawal.setSum(createWithdrawalInput.getSum());
        }
        if (createWithdrawalInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            withdrawal.setUser(createWithdrawalInput.getUser());
        }

        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal update(UpdateWithdrawalInput updateWithdrawalInput) {
        Withdrawal withdrawal = getById(updateWithdrawalInput.getId());

        if (updateWithdrawalInput.getAccountHolder() != null
            && !withdrawal.getAccountHolder().equals(updateWithdrawalInput.getAccountHolder())
            && !updateWithdrawalInput.getAccountHolder().isBlank()) {
            withdrawal.setAccountHolder(updateWithdrawalInput.getAccountHolder());
        }
        if (updateWithdrawalInput.getAccountNumber() != null
            && !updateWithdrawalInput.getAccountNumber().isBlank()
            && !withdrawal.getAccountNumber().equals(updateWithdrawalInput.getAccountNumber())) {
            withdrawal.setAccountNumber(updateWithdrawalInput.getAccountNumber());
        }
        if (updateWithdrawalInput.getNameOfBank() != null
            && !updateWithdrawalInput.getNameOfBank().isBlank()
            && !withdrawal.getNameOfBank().equals(updateWithdrawalInput.getNameOfBank())) {
            withdrawal.setNameOfBank(updateWithdrawalInput.getNameOfBank());
        }
        if (updateWithdrawalInput.getStatus() != null
            && !withdrawal.getStatus().equals(updateWithdrawalInput.getStatus())) {
            withdrawal.setStatus(updateWithdrawalInput.getStatus());
        }
        if (updateWithdrawalInput.getSum() != null
            && !withdrawal.getSum().equals(updateWithdrawalInput.getSum())) {
            withdrawal.setSum(updateWithdrawalInput.getSum());
        }
        if (updateWithdrawalInput.getUser() != null
            && !withdrawal.getUser().equals(updateWithdrawalInput.getUser())) {
            withdrawal.setUser(updateWithdrawalInput.getUser());
        }

        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Boolean deleteById (Long id) {
        withdrawalRepository.deleteById(id);
        return true;
    }
}
