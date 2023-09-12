package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.EStatus;
import com.habsida.moragoproject.model.input.CreateDepositInput;
import com.habsida.moragoproject.model.input.UpdateDepositInput;
import com.habsida.moragoproject.repository.DepositRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;

@Service
public class DepositServiceImpl implements DepositService{

    private DepositRepository depositRepository;
    private ModelMapper modelMapper;

    public DepositServiceImpl(DepositRepository depositRepository, ModelMapper modelMapper) {
        this.depositRepository = depositRepository;
        this.modelMapper = modelMapper;
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
    public Deposit create(CreateDepositInput createDepositInput) {
        Deposit deposit = modelMapper.map(createDepositInput, Deposit.class);

        if (deposit.getAccountHolder() == null) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        }
        if (deposit.getCoin() == null) {
            throw new IllegalArgumentException("field coin cannot be null");
        }
        if (deposit.getNameOfBank() == null) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        }
        if (deposit.getStatus() == null) {
            throw new IllegalArgumentException("field status cannot be null");
        }
        if (deposit.getWon() == null) {
            throw new IllegalArgumentException("field won cannot be null");
        }
        if (deposit.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }

        return depositRepository.save(deposit);
    }

    @Override
    public Deposit update(UpdateDepositInput updateDepositInput) {
        Deposit deposit = getById(updateDepositInput.getId());
        modelMapper.map(updateDepositInput, deposit);

        if (deposit.getAccountHolder() == null) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        }
        if (deposit.getCoin() == null) {
            throw new IllegalArgumentException("field coin cannot be null");
        }
        if (deposit.getNameOfBank() == null) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        }
        if (deposit.getStatus() == null) {
            throw new IllegalArgumentException("field status cannot be null");
        }
        if (deposit.getWon() == null) {
            throw new IllegalArgumentException("field won cannot be null");
        }
        if (deposit.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }

        return depositRepository.save(deposit);
    }

    @Override
    public Boolean deleteById(Long id) {
        depositRepository.deleteById(id);
        return true;
    }
}
