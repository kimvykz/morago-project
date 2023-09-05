package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.repository.DebtorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtorServiceImpl implements DebtorService{

    private DebtorRepository debtorRepository;

    public DebtorServiceImpl(DebtorRepository debtorRepository) {
        this.debtorRepository = debtorRepository;
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
    public Debtor create(Debtor debtor) {
        return debtorRepository.save(debtor);
    }

    @Override
    public Debtor update(Debtor debtor) {
        return debtorRepository.save(debtor);
    }

    @Override
    public Boolean deleteById(Long id) {
        debtorRepository.deleteById(id);
        return true;
    }
}
