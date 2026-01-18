package com.challenge.nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.nubank.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

}
