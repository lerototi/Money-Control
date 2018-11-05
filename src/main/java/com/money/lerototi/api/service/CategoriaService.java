package com.money.lerototi.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.money.lerototi.api.model.Categoria;
import com.money.lerototi.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria atualizar(Long codigo, Categoria categoria) {
	
		Categoria categoriaSalva = buscarPorCategoria(codigo);
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		return categoriaRepository.save(categoriaSalva);
	}

	private Categoria buscarPorCategoria(Long codigo) {
		Categoria categoriaRetornada = categoriaRepository.getOne(codigo);
		
		if (categoriaRetornada == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return categoriaRetornada;
		
	}

	
	
}
