package org.example.financeiro.selic.service;


import org.example.financeiro.selic.domain.Selic;
import org.example.financeiro.selic.model.SelicInput;
import org.example.financeiro.selic.repository.SelicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelicService {

    @Autowired
    private SelicRepository repository;

    public void create(SelicInput input) {
        repository.save(new Selic(input));
    }

    public List<Selic> getAll() {
        return repository.findAll();
    }
}
