package org.example.saldoativo.controllers;

import org.example.movimentacao.model.SaldoAtivoOutput;
import org.example.saldoativo.service.SaldoAtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("saldo-ativo")
public class SaldoAtivoController {

    @Autowired
    private SaldoAtivoService service;

    @GetMapping
    public ResponseEntity<List<SaldoAtivoOutput>> list(@RequestParam LocalDate dataInicial, @RequestParam LocalDate dataFinal) {
        return ResponseEntity.ok(service.calculaSaldo(dataInicial, dataFinal));
    }

}
