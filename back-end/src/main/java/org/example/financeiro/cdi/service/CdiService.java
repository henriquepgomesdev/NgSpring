package org.example.financeiro.cdi.service;


import org.example.financeiro.cdi.domain.Cdi;
import org.example.financeiro.cdi.model.CdiInput;
import org.example.financeiro.cdi.repository.CdiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CdiService {

    @Autowired
    private CdiRepository repository;

    public void create(CdiInput input) {
        repository.save(new Cdi(input));
    }

    public List<Cdi> getAll() {
        return repository.findAll();
    }
}
