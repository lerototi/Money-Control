package com.money.lerototi.api.repository.lancamento;

import java.util.List;

import com.money.lerototi.api.model.Lancamento;
import com.money.lerototi.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
	
}
