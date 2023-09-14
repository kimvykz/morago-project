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
    public Deposit create(CreateDepositInput createDepositInput) {
        Deposit deposit = new Deposit();

        if (createDepositInput.getAccountHolder() == null
            || createDepositInput.getAccountHolder().isBlank()) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        } else {
            deposit.setAccountHolder(createDepositInput.getAccountHolder());
        }
        if (createDepositInput.getCoin() == null) {
            throw new IllegalArgumentException("field coin cannot be null");
        } else {
            deposit.setCoin(createDepositInput.getCoin());
        }
        if (createDepositInput.getNameOfBank() == null
            || createDepositInput.getNameOfBank().isBlank()) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        } else {
            deposit.setNameOfBank(createDepositInput.getNameOfBank());
        }
        if (createDepositInput.getStatus() == null) {
            throw new IllegalArgumentException("field status cannot be null");
        } else {
            deposit.setStatus(createDepositInput.getStatus());
        }
        if (createDepositInput.getWon() == null) {
            throw new IllegalArgumentException("field won cannot be null");
        } else {
            deposit.setWon(createDepositInput.getWon());
        }
        if (createDepositInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            deposit.setUser(createDepositInput.getUser());
        }

        return depositRepository.save(deposit);
    }

    @Override
    public Deposit update(UpdateDepositInput updateDepositInput) {
        Deposit deposit = getById(updateDepositInput.getId());

        if (updateDepositInput.getAccountHolder() != null
            && !deposit.getAccountHolder().equals(updateDepositInput.getAccountHolder())
            && !updateDepositInput.getAccountHolder().isBlank()) {
            deposit.setAccountHolder(updateDepositInput.getAccountHolder());
        }
        if (updateDepositInput.getCoin() != null
            && !deposit.getCoin().equals(updateDepositInput.getCoin())) {
            deposit.setCoin(updateDepositInput.getCoin());
        }
        if (updateDepositInput.getNameOfBank() != null
            && !updateDepositInput.getNameOfBank().isBlank()
            && !deposit.getNameOfBank().equals(updateDepositInput.getNameOfBank())) {
            deposit.setNameOfBank(updateDepositInput.getNameOfBank());
        }
        if (updateDepositInput.getStatus() != null
            && !deposit.getStatus().equals(updateDepositInput.getStatus())) {
            deposit.setStatus(updateDepositInput.getStatus());
        }
        if (updateDepositInput.getWon() != null
            && !deposit.getWon().equals(updateDepositInput.getWon())) {
            deposit.setWon(updateDepositInput.getWon());
        }
        if (updateDepositInput.getUser() != null
            && !deposit.getUser().equals(updateDepositInput.getUser())) {
            deposit.setUser(updateDepositInput.getUser());
        }

        return depositRepository.save(deposit);
    }

    @Override
    public Boolean deleteById(Long id) {
        depositRepository.deleteById(id);
        return true;
    }
}
