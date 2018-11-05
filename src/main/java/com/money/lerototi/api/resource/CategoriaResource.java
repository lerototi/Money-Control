package com.money.lerototi.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.money.lerototi.api.model.Categoria;
import com.money.lerototi.api.repository.CategoriaRepository;
import com.money.lerototi.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping	
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;

	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		Categoria categoriaRetornada = categoriaRepository.findById(codigo).orElse(null);
		
		return categoriaRetornada != null ? ResponseEntity.ok(categoriaRetornada) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarCategoria(@PathVariable Long codigo) {
		categoriaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> alterarCategoria(@PathVariable Long codigo, @RequestBody Categoria categoria){
		
		Categoria categoriaAtualizada = categoriaService.atualizar(codigo, categoria);
		return ResponseEntity.ok(categoriaAtualizada);
	}
	
}
