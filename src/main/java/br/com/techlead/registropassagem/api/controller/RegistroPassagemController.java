package br.com.techlead.registropassagem.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.techlead.registropassagem.api.event.RecursoCriadoEvento;
import br.com.techlead.registropassagem.api.model.RegistroPassagem;
import br.com.techlead.registropassagem.api.model.dto.RegistroPassagemResumoDTO;
import br.com.techlead.registropassagem.api.repository.filter.RegistroPassagemFilter;
import br.com.techlead.registropassagem.api.service.RegistroPassagemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api("RegistroPassagem")
@RestController
@RequestMapping("/registropassagem")
public class RegistroPassagemController {

	@Autowired
	private RegistroPassagemService registroPassagemService;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@ApiOperation(  consumes = "application/json", produces = "application/json", notes = "Consulta as passagens de forma resumida sem incluir os retornos da Receita Federal e mensagem xml enviada", value="Consulta os registros de passagens realizados ")
	@GetMapping
	public Page<RegistroPassagemResumoDTO> consultarPassagens(RegistroPassagemFilter filtro, Pageable page){
		return registroPassagemService.consultarPassagem(filtro, page);
	}
	
	@ApiOperation(consumes = "application/json", produces = "application/json", notes = "Registra a passagem de um origem definida (posto ou antena) a partir da placa ou chave MDFe", value="Realiza um registro de passagem ")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<RegistroPassagem> criarRegistroPassagem(@Valid @RequestBody RegistroPassagem registroPassagem, HttpServletResponse response) {
		RegistroPassagem salvo=registroPassagemService.salvarRegistroPassagem(registroPassagem);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, salvo.getIdRegistroPassagem()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	
	
	@ApiOperation("Detalhar o registro (mensagem de envio e recebimento da Receita Federal) ")
	@GetMapping("/{codigo}")
	public ResponseEntity<RegistroPassagem> detalharRegistroPassagemChave(@PathVariable Long codigo) {
		RegistroPassagem retorno=registroPassagemService.detalharRegistroPassagem(codigo);
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping("/batch/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void registrarBatch(@PathVariable Long codigo) {
		registroPassagemService.processarBatch(codigo, null);
	}
	
	@PostMapping("/batch/{codigo}/registrador/{registrador}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void registrarBatchRegistrador(@PathVariable(name="codigo") Long codigo, @PathVariable(name="registrador") Long registrador) {
		registroPassagemService.processarBatch(codigo, registrador);
	}
	
}
