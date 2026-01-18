package com.challenge.nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.nubank.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
