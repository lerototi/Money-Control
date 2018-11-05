package com.money.lerototi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.money.lerototi.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
