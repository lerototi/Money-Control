package com.money.lerototi.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.money.lerototi.api.model.Pessoa;
import com.money.lerototi.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResources {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping
	private ResponseEntity<Pessoa> criarPessoa(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response){
		
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@GetMapping("/{codigo}")
	private ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Long codigo) {
		 
		Pessoa pessoaRetornada = pessoaRepository.findById(codigo).orElse(null);
	
		return pessoaRetornada != null ? ResponseEntity.ok(pessoaRetornada) : ResponseEntity.notFound().build();
		
	}
	
	

}
