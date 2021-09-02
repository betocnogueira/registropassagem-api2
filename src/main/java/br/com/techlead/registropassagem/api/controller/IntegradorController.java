package br.com.techlead.registropassagem.api.controller;

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

import br.com.techlead.registropassagem.api.event.RecursoCriadoEvento;
import br.com.techlead.registropassagem.api.model.Integrador;
import br.com.techlead.registropassagem.api.model.dto.IntegradorDTO;
import br.com.techlead.registropassagem.api.service.IntegradorService;
import io.swagger.annotations.Api;


@Api(tags = "Integradores")
@RestController
@RequestMapping("/integrador")
public class IntegradorController {

	@Autowired
	private IntegradorService integradorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Integrador> listar(){
		return integradorService.consultarIntegrador();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Integrador> criarIntegrador(@Valid @RequestBody IntegradorDTO integradorDTO, HttpServletResponse response) {
		Integrador integrador=new Integrador();
		BeanUtils.copyProperties(integradorDTO, integrador);
		Integrador integradorSalvo=integradorService.criarIntegrador(integrador);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, integradorSalvo.getIdIntegrador()));
		return ResponseEntity.status(HttpStatus.CREATED).body(integradorSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Integrador> alterarIntegrador(@PathVariable Long codigo,@Valid @RequestBody IntegradorDTO integradorDTO) {
		Integrador integrador=new Integrador();
		BeanUtils.copyProperties(integradorDTO, integrador);
		Integrador salvo=integradorService.alterarIntegrador(integrador, codigo);
		return ResponseEntity.ok(salvo);
		
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Integrador> detalharIntegrador(@PathVariable Long codigo) {
		Integrador integrador=integradorService.buscarIntegradorPeloCodigo(codigo);
		return ResponseEntity.ok(integrador);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirIntegrador(@PathVariable Long codigo) {
		integradorService.excluirIntegrador(codigo);
		
	}
}
