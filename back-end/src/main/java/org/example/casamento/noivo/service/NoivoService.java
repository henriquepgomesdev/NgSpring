package org.example.casamento.noivo.service;


import org.example.casamento.noivo.domain.Noivo;
import org.example.casamento.noivo.model.NoivoInput;
import org.example.casamento.noivo.repository.NoivoRepository;
import org.example.user.UsersAutenticatedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoivoService {

    @Autowired
    private NoivoRepository repository;

    @Autowired
    private UsersAutenticatedUtils usersAutenticatedUtils;

    public void create(NoivoInput input) {
        Noivo movimentacao = new Noivo();
        repository.save(movimentacao);
    }

    public List<Noivo> getAll() {
        return repository.findAll();
    }
}
