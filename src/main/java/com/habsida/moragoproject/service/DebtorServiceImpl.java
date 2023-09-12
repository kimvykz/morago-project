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
    private ModelMapper modelMapper;

    public DebtorServiceImpl(DebtorRepository debtorRepository, ModelMapper modelMapper) {
        this.debtorRepository = debtorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Debtor> getAll() {
        return debtorRepository.findAll();
    }

    @Override
    public Page<Debtor> getAllPaged(PageRequest pageRequest) {
        return debtorRepository.findAll(pageRequest);
    }

    @Override
    public Debtor getById(Long id) {
        return debtorRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Debtor is not found for the id - " + id);
        });
    }

    @Override
    public Debtor create(CreateDebtorInput createDebtorInput) {
        Debtor debtor = modelMapper.map(createDebtorInput, Debtor.class);
        if (debtor.getAccountHolder() == null) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        }
        if (debtor.getIsPaid() == null) {
            throw new IllegalArgumentException("field isPaid cannot be null");
        }
        if (debtor.getNameOfBank() == null) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        }
        if (debtor.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }

        return debtorRepository.save(debtor);
    }

    @Override
    public Debtor update(UpdateDebtorInput updateDebtorInput) {
        Debtor debtor = getById(updateDebtorInput.getId());
        modelMapper.map(updateDebtorInput, debtor);

        if (debtor.getAccountHolder() == null) {
            throw new IllegalArgumentException("field accountHolder cannot be null");
        }
        if (debtor.getIsPaid() == null) {
            throw new IllegalArgumentException("field isPaid cannot be null");
        }
        if (debtor.getNameOfBank() == null) {
            throw new IllegalArgumentException("field nameOfBank cannot be null");
        }
        if (debtor.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }
        return debtorRepository.save(debtor);
    }

    @Override
    public Boolean deleteById(Long id) {
        debtorRepository.deleteById(id);
        return true;
    }
}
