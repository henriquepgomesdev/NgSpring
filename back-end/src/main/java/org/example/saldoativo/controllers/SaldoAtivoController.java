package org.example.saldoativo.controllers;

import jakarta.validation.Valid;
import org.example.movimentacao.domain.Movimentacao;
import org.example.movimentacao.enums.TipoMovimentacao;
import org.example.movimentacao.model.MovimentacaoInput;
import org.example.movimentacao.model.MovimentacaoOutput;
import org.example.movimentacao.model.SaldoAtivoOutput;
import org.example.movimentacao.service.MovimentacaoService;
import org.example.saldoativo.service.SaldoAtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("saldo-ativo")
public class SaldoAtivoController {

    @Autowired
    private SaldoAtivoService service;

    @GetMapping
    public ResponseEntity<List<SaldoAtivoOutput>> list() {
        System.out.println("Requisicao ok");
        return ResponseEntity.ok(service.calculaSaldo());
    }

}
