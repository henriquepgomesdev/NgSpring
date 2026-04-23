package org.example.financeiro.cdi.controllers;

import jakarta.validation.Valid;
import org.example.financeiro.cdi.domain.Cdi;
import org.example.financeiro.cdi.model.CdiDto;
import org.example.financeiro.cdi.model.CdiInput;
import org.example.financeiro.cdi.service.CdiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cdi")
public class CdiController {

    @Autowired
    private CdiService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CdiInput input) {
        service.create(input);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<CdiDto>> list() {
        System.out.println("Requisicao ok");
        return ResponseEntity.ok(service.getAll());
    }

}
