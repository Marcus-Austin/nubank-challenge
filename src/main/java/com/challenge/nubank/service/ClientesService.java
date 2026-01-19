package com.challenge.nubank.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.nubank.dto.ClientesDto;
import com.challenge.nubank.dto.ClientesResponseDto;
import com.challenge.nubank.dto.ContatoResponseDto;
import com.challenge.nubank.model.Clientes;
import com.challenge.nubank.model.Contato;
import com.challenge.nubank.repository.ClientesRepository;
@Service
public class ClientesService {

    @Autowired
    private ClientesRepository repository;

    
    public Clientes salvar(ClientesDto dto){
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if(dto.getContatos() != null && dto.getContatos().size() > 0){
            List<Contato> contatos = dto.getContatos().stream().map(c -> {
                Contato contato = new Contato();
                contato.setTelefone(c.getTelefone());
                contato.setEmail(c.getEmail());
                contato.setClientes(clientes);
                return contato;
            }).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }
        return repository.save(clientes);
    }

    public List<ClientesResponseDto> listarTodos(){
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ContatoResponseDto> listarContatosPorCliente(long clienteId){
        Clientes cliente = repository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getContatos().stream().map(c -> {
            ContatoResponseDto dto = new ContatoResponseDto();
            dto.setId(c.getId());
            dto.setTelefone(c.getTelefone());
            dto.setEmail(c.getEmail());
            return dto;
        }).collect(Collectors.toList());

    }

    private ClientesResponseDto toDto(Clientes cliente){
     ClientesResponseDto dto = new ClientesResponseDto();
     dto.setId(cliente.getId());
     dto.setNome(cliente.getNome());
     List<ContatoResponseDto> contatos = cliente.getContatos().stream().map(c -> {
                ContatoResponseDto contatoDto = new ContatoResponseDto();
                contatoDto.setId(c.getId());
                contatoDto.setTelefone(c.getTelefone());
                contatoDto.setEmail(c.getEmail());
                return contatoDto;
            }).collect(Collectors.toList());
            dto.setContatos(contatos);
            return dto;
    }



}
