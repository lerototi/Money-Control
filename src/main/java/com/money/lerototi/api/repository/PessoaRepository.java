package com.money.lerototi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.money.lerototi.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
 
}
