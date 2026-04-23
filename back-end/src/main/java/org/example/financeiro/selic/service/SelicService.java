package org.example.financeiro.selic.service;


import org.example.financeiro.cdi.model.CdiDto;
import org.example.financeiro.selic.domain.Selic;
import org.example.financeiro.selic.mapper.SelicMapper;
import org.example.financeiro.selic.model.SelicDto;
import org.example.financeiro.selic.model.SelicInput;
import org.example.financeiro.selic.repository.SelicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelicService {

    @Autowired
    private SelicRepository repository;

    @Autowired
    private SelicMapper mapper;

    public void create(SelicInput input) {
        repository.save(new Selic(input));
    }

    public List<SelicDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
