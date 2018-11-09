package com.money.lerototi.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.money.lerototi.api.event.RecursoCriadoEvent;
import com.money.lerototi.api.excepltionhandle.MoneyExceptionHandler.Erro;
import com.money.lerototi.api.model.Lancamento;
import com.money.lerototi.api.repository.LancamentoRepository;
import com.money.lerototi.api.repository.filter.LancamentoFilter;
import com.money.lerototi.api.repository.projection.ResumoLancamento;
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
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable pageable){
		
		return lancamentoRepository.filtrar(filter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable){
		
		return lancamentoRepository.resumir(filter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo){
		
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criarLancamento(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
		
		Lancamento lancamentoRetornado = lancamentoService.save(lancamento);
		publish.publishEvent(new RecursoCriadoEvent(this, response, lancamentoRetornado.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoRetornado);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarLancamento(@PathVariable Long codigo) {
		
		lancamentoRepository.delete(codigo);
		
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
				
		String mensagemDesenvolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	
	
}
