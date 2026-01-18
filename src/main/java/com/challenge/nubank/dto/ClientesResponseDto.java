package com.challenge.nubank.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientesResponseDto {

    private Long id;
    private String nome;
    private List<ContatoResponseDto> contatos;

}
