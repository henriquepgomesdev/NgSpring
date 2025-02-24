package org.example.financeiro.selic.controllers;

import jakarta.validation.Valid;
import org.example.financeiro.selic.domain.Selic;
import org.example.financeiro.selic.model.SelicInput;
import org.example.financeiro.selic.service.SelicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("selic")
public class SelicController {

    @Autowired
    private SelicService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid SelicInput input) {
        System.out.println(input);
        service.create(input);
        System.out.println("Cadastro ok");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Selic>> list() {
        System.out.println("Requisicao ok");
        return ResponseEntity.ok(service.getAll());
    }

}
