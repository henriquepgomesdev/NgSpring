package org.example.calculadorarentabilidade.controllers;

import jakarta.validation.Valid;
import org.example.calculadorarentabilidade.model.CalculadoraRentabilidadeInput;
import org.example.calculadorarentabilidade.service.CalculadoraRentabilidadeService;
import org.example.movimentacao.enums.TipoMovimentacao;
import org.example.movimentacao.model.MovimentacaoInput;
import org.example.movimentacao.model.MovimentacaoOutput;
import org.example.movimentacao.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
