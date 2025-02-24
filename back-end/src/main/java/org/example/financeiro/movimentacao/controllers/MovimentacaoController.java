package org.example.financeiro.movimentacao.controllers;

import jakarta.validation.Valid;
import org.example.financeiro.movimentacao.enums.TipoMovimentacao;
import org.example.financeiro.movimentacao.model.MovimentacaoInput;
import org.example.financeiro.movimentacao.model.MovimentacaoOutput;
import org.example.financeiro.movimentacao.service.MovimentacaoService;
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
@RequestMapping("movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService service;

    @GetMapping("/tipos-movimentacao")
    public List<Map<String, Object>> listarTiposMovimentacao() {
        List<Map<String, Object>> result = new ArrayList<>();

        for (TipoMovimentacao tipo : TipoMovimentacao.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tipo.getId());
            map.put("descricao", tipo.getDescription());
            result.add(map);
        }

        return result;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MovimentacaoInput input) {
        System.out.println(input);
        service.create(input);
        System.out.println("Cadastro ok");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<MovimentacaoOutput>> list() {
        System.out.println("Requisicao ok");
        return ResponseEntity.ok(service.getAllMovimentacoes());
    }

}
