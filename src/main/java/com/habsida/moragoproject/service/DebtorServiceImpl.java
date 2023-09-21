package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.model.input.DebtorCreateInput;
import com.habsida.moragoproject.model.input.DebtorUpdateInput;
import com.habsida.moragoproject.repository.DebtorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Debtor create (DebtorCreateInput debtorCreateInput) {
        Debtor debtor = new Debtor();

        if (debtorCreateInput.getAccountHolder() == null
            || debtorCreateInput.getAccountHolder().isBlank()) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        } else {
            debtor.setAccountHolder(debtorCreateInput.getAccountHolder());
        }
        if (debtorCreateInput.getIsPaid() == null) {
            throw new IllegalArgumentException("field isPaid cannot be null");
        } else {
            debtor.setAccountHolder(debtorCreateInput.getAccountHolder());
        }
        if (debtorCreateInput.getNameOfBank() == null
            || debtorCreateInput.getNameOfBank().isBlank()) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        } else {
            debtor.setNameOfBank(debtorCreateInput.getNameOfBank());
        }
        if (debtorCreateInput.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        } else {
            debtor.setUser(debtorCreateInput.getUser());
        }

        return debtorRepository.save(debtor);
    }

    @Override
    public Debtor update (DebtorUpdateInput debtorUpdateInput) {
        Debtor debtor = getById(debtorUpdateInput.getId());

        if (debtorUpdateInput.getAccountHolder() != null
            && !debtor.getAccountHolder().equals(debtorUpdateInput.getAccountHolder())
            && !debtorUpdateInput.getAccountHolder().isBlank()) {
            debtor.setAccountHolder(debtorUpdateInput.getAccountHolder());
        }
        if (debtorUpdateInput.getIsPaid() != null
            && !debtor.getIsPaid().equals(debtorUpdateInput.getIsPaid())) {
            debtor.setIsPaid(debtorUpdateInput.getIsPaid());
        }
        if (debtorUpdateInput.getNameOfBank() != null
            && !debtorUpdateInput.getNameOfBank().isBlank()
            && !debtor.getNameOfBank().equals(debtorUpdateInput.getNameOfBank())) {
            debtor.setNameOfBank(debtorUpdateInput.getNameOfBank());
        }
        if (debtorUpdateInput.getUser() != null
            && !debtor.getUser().equals(debtorUpdateInput.getUser())) {
            debtor.setUser(debtorUpdateInput.getUser());
        }
        return debtorRepository.save(debtor);
    }

    @Override
    public Boolean deleteById (Long id) {
        debtorRepository.deleteById(id);
        return true;
    }
}
