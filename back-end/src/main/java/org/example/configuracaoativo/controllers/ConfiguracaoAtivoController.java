package org.example.configuracaoativo.controllers;

import jakarta.validation.Valid;
import org.example.configuracaoativo.domain.ConfiguracaoAtivo;
import org.example.configuracaoativo.model.ConfiguracaoAtivoInput;
import org.example.configuracaoativo.service.ConfiguracaoAtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("configuracao-ativo")
public class ConfiguracaoAtivoController {

    @Autowired
    private ConfiguracaoAtivoService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid ConfiguracaoAtivoInput input) {
        System.out.println(input);
        service.create(input);
        System.out.println("Cadastro ok");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ConfiguracaoAtivo>> list() {
        System.out.println("Requisicao ok");
        return ResponseEntity.ok(service.getAll());
    }

}
