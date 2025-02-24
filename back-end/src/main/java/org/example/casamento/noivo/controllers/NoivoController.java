package org.example.casamento.noivo.controllers;

import jakarta.validation.Valid;
import org.example.casamento.noivo.model.NoivoInput;
import org.example.casamento.noivo.service.NoivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("noivo")
public class NoivoController {

    @Autowired
    private NoivoService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid NoivoInput input) {
        service.create(input);
        return ResponseEntity.ok().build();
    }
}
