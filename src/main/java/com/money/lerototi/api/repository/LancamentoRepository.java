package com.money.lerototi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.money.lerototi.api.model.Lancamento;
import com.money.lerototi.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
