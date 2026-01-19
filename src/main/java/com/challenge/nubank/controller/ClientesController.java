package com.challenge.nubank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.nubank.dto.ClientesDto;
import com.challenge.nubank.dto.ClientesResponseDto;
import com.challenge.nubank.dto.ContatoResponseDto;
import com.challenge.nubank.model.Clientes;
import com.challenge.nubank.service.ClientesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService service;

    @PostMapping
    public ResponseEntity<Clientes> criarCliente(@RequestBody ClientesDto dto) {
        Clientes novoCliente = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDto>> listaTodos() {
        return  ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoResponseDto>> listarContatos(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarContatosPorCliente(id));
    }

}
