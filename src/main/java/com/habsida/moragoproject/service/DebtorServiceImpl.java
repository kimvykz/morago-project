package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateDebtorInput;
import com.habsida.moragoproject.model.input.UpdateDebtorInput;
import com.habsida.moragoproject.repository.DebtorRepository;
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
public class DebtorServiceImpl implements DebtorService{

    private DebtorRepository debtorRepository;

    public DebtorServiceImpl (DebtorRepository debtorRepository) {
        this.debtorRepository = debtorRepository;
    }

    @Override
    public List<Debtor> getAll () {
        return debtorRepository.findAll();
    }

    @Override
    public Page<Debtor> getAllByPaging (PageRequest pageRequest) {
        return debtorRepository.findAll(pageRequest);
    }

    @Override
    public Debtor getById (Long id) {
        return debtorRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Debtor is not found for the id - " + id);
        });
    }

    @Override
    public Debtor create (CreateDebtorInput createDebtorInput) {
        Debtor debtor = new Debtor();

        if (createDebtorInput.getAccountHolder() == null
            || createDebtorInput.getAccountHolder().isBlank()) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        } else {
            debtor.setAccountHolder(createDebtorInput.getAccountHolder());
        }
        if (createDebtorInput.getIsPaid() == null) {
            throw new IllegalArgumentException("field isPaid cannot be null");
        } else {
            debtor.setAccountHolder(createDebtorInput.getAccountHolder());
        }
        if (createDebtorInput.getNameOfBank() == null
            || createDebtorInput.getNameOfBank().isBlank()) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        } else {
            debtor.setNameOfBank(createDebtorInput.getNameOfBank());
        }
        if (createDebtorInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            debtor.setUser(createDebtorInput.getUser());
        }

        return debtorRepository.save(debtor);
    }

    @Override
    public Debtor update (UpdateDebtorInput updateDebtorInput) {
        Debtor debtor = getById(updateDebtorInput.getId());

        if (updateDebtorInput.getAccountHolder() != null
            && !debtor.getAccountHolder().equals(updateDebtorInput.getAccountHolder())
            && !updateDebtorInput.getAccountHolder().isBlank()) {
            debtor.setAccountHolder(updateDebtorInput.getAccountHolder());
        }
        if (updateDebtorInput.getIsPaid() != null
            && !debtor.getIsPaid().equals(updateDebtorInput.getIsPaid())) {
            debtor.setIsPaid(updateDebtorInput.getIsPaid());
        }
        if (updateDebtorInput.getNameOfBank() != null
            && !updateDebtorInput.getNameOfBank().isBlank()
            && !debtor.getNameOfBank().equals(updateDebtorInput.getNameOfBank())) {
            debtor.setNameOfBank(updateDebtorInput.getNameOfBank());
        }
        if (updateDebtorInput.getUser() != null
            && !debtor.getUser().equals(updateDebtorInput.getUser())) {
            debtor.setUser(updateDebtorInput.getUser());
        }
        return debtorRepository.save(debtor);
    }

    @Override
    public Boolean deleteById (Long id) {
        debtorRepository.deleteById(id);
        return true;
    }
}
