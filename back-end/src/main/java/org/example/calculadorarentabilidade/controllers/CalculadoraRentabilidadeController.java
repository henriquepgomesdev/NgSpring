package org.example.calculadorarentabilidade.controllers;

import org.example.calculadorarentabilidade.model.CalculadoraRentabilidadeInput;
import org.example.calculadorarentabilidade.service.CalculadoraRentabilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calculadora-rentabilidade")
public class CalculadoraRentabilidadeController {

    @Autowired
    private CalculadoraRentabilidadeService service;

    @GetMapping
    public ResponseEntity listarTiposMovimentacao(CalculadoraRentabilidadeInput calculadoraRentabilidadeInput) {
        service.calculaValor(calculadoraRentabilidadeInput);
        return ResponseEntity.ok().build();
    }

}
