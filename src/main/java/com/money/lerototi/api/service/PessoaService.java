package com.money.lerototi.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.money.lerototi.api.model.Pessoa;
import com.money.lerototi.api.repository.PessoaRepository;


@Service
public class PessoaService {

	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa PessoaSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, PessoaSalva, "codigo");
		return pessoaRepository.save(PessoaSalva);
	}

	

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
	
	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa PessoaSalva = pessoaRepository.findById(codigo).orElse(null);
		if (PessoaSalva==null) {
			throw new EmptyResultDataAccessException(1);
		}
		return PessoaSalva;
	}
	
}
