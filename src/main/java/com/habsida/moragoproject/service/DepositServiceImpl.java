package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.model.input.DepositCreateInput;
import com.habsida.moragoproject.model.input.DepositUpdateInput;
import com.habsida.moragoproject.repository.DepositRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService{

    private DepositRepository depositRepository;

    public DepositServiceImpl (DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public List<Deposit> getAll () {
        return depositRepository.findAll();
    }

    @Override
    public Page<Deposit> getAllByPaging (PageRequest pageRequest) {
        return depositRepository.findAll(pageRequest);
    }

    @Override
    public Deposit getById (Long id) {
        return depositRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Deposit is not found for the id - " + id);
        });
    }

    @Override
    public Deposit create (DepositCreateInput depositCreateInput) {
        Deposit deposit = new Deposit();

        if (depositCreateInput.getAccountHolder() == null
            || depositCreateInput.getAccountHolder().isBlank()) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        } else {
            deposit.setAccountHolder(depositCreateInput.getAccountHolder());
        }
        if (depositCreateInput.getCoin() == null) {
            throw new IllegalArgumentException("field coin cannot be null");
        } else {
            deposit.setCoin(depositCreateInput.getCoin());
        }
        if (depositCreateInput.getNameOfBank() == null
            || depositCreateInput.getNameOfBank().isBlank()) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        } else {
            deposit.setNameOfBank(depositCreateInput.getNameOfBank());
        }
        if (depositCreateInput.getStatus() == null) {
            throw new IllegalArgumentException("field status cannot be null");
        } else {
            deposit.setStatus(depositCreateInput.getStatus());
        }
        if (depositCreateInput.getWon() == null) {
            throw new IllegalArgumentException("field won cannot be null");
        } else {
            deposit.setWon(depositCreateInput.getWon());
        }
        if (depositCreateInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            deposit.setUser(depositCreateInput.getUser());
        }

        return depositRepository.save(deposit);
    }

    @Override
    public Deposit update (DepositUpdateInput depositUpdateInput) {
        Deposit deposit = getById(depositUpdateInput.getId());

        if (depositUpdateInput.getAccountHolder() != null
            && !deposit.getAccountHolder().equals(depositUpdateInput.getAccountHolder())
            && !depositUpdateInput.getAccountHolder().isBlank()) {
            deposit.setAccountHolder(depositUpdateInput.getAccountHolder());
        }
        if (depositUpdateInput.getCoin() != null
            && !deposit.getCoin().equals(depositUpdateInput.getCoin())) {
            deposit.setCoin(depositUpdateInput.getCoin());
        }
        if (depositUpdateInput.getNameOfBank() != null
            && !depositUpdateInput.getNameOfBank().isBlank()
            && !deposit.getNameOfBank().equals(depositUpdateInput.getNameOfBank())) {
            deposit.setNameOfBank(depositUpdateInput.getNameOfBank());
        }
        if (depositUpdateInput.getStatus() != null
            && !deposit.getStatus().equals(depositUpdateInput.getStatus())) {
            deposit.setStatus(depositUpdateInput.getStatus());
        }
        if (depositUpdateInput.getWon() != null
            && !deposit.getWon().equals(depositUpdateInput.getWon())) {
            deposit.setWon(depositUpdateInput.getWon());
        }
        if (depositUpdateInput.getUser() != null
            && !deposit.getUser().equals(depositUpdateInput.getUser())) {
            deposit.setUser(depositUpdateInput.getUser());
        }

        return depositRepository.save(deposit);
    }

    @Override
    public Boolean deleteById (Long id) {
        depositRepository.deleteById(id);
        return true;
    }
}
