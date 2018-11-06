package com.money.lerototi.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.money.lerototi.api.event.RecursoCriadoEvent;
import com.money.lerototi.api.excepltionhandle.MoneyExceptionHandler.Erro;
import com.money.lerototi.api.model.Lancamento;
import com.money.lerototi.api.repository.LancamentoRepository;
import com.money.lerototi.api.repository.filter.LancamentoFilter;
import com.money.lerototi.api.service.LancamentoService;
import com.money.lerototi.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publish;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<Lancamento> pesquisar(LancamentoFilter filter){
		
		return lancamentoRepository.filtrar(filter);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo){
		
		Lancamento lancamento = lancamentoRepository.findById(codigo).orElse(null);
		
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criarLancamento(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
		
		Lancamento lancamentoRetornado = lancamentoService.save(lancamento);
		publish.publishEvent(new RecursoCriadoEvent(this, response, lancamentoRetornado.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoRetornado);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
				
		String mensagemDesenvolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	
	
}
