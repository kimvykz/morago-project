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
    private ModelMapper modelMapper;

    public WithdrawalServiceImpl (WithdrawalRepository withdrawalRepository, ModelMapper modelMapper) {
        this.withdrawalRepository = withdrawalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Withdrawal> getAll () {
        return withdrawalRepository.findAll();
    }

    @Override
    public Page<Withdrawal> getAllPaged(PageRequest pageRequest) {
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
        Withdrawal withdrawal = modelMapper.map(createWithdrawalInput, Withdrawal.class);
        if (withdrawal.getAccountHolder() == null) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        }
        if (withdrawal.getAccountNumber() == null) {
            throw new IllegalArgumentException("field accountNumber cannot be null");
        }
        if (withdrawal.getNameOfBank() == null) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        }
        if (withdrawal.getStatus() == null) {
            throw new IllegalArgumentException("field status cannot be null");
        }
        if (withdrawal.getSum() == null) {
            throw new IllegalArgumentException("field sum cannot be null");
        }
        if (withdrawal.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }

        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal update(UpdateWithdrawalInput updateWithdrawalInput) {
        Withdrawal withdrawal = getById(updateWithdrawalInput.getId());
        modelMapper.map(updateWithdrawalInput, withdrawal);

        if (withdrawal.getAccountHolder() == null) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        }
        if (withdrawal.getAccountNumber() == null) {
            throw new IllegalArgumentException("field accountNumber cannot be null");
        }
        if (withdrawal.getNameOfBank() == null) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        }
        if (withdrawal.getStatus() == null) {
            throw new IllegalArgumentException("field status cannot be null");
        }
        if (withdrawal.getSum() == null) {
            throw new IllegalArgumentException("field sum cannot be null");
        }
        if (withdrawal.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }

        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Boolean deleteById(Long id) {
        withdrawalRepository.deleteById(id);
        return true;
    }
}
