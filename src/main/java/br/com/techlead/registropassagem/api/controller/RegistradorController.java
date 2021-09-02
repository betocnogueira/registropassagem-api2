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
import br.com.techlead.registropassagem.api.model.Registrador;
import br.com.techlead.registropassagem.api.model.dto.RegistradorDTO;
import br.com.techlead.registropassagem.api.service.RegistradorService;
import io.swagger.annotations.Api;

@Api(tags = "Registrador")
@RestController
@RequestMapping("/registrador")
public class RegistradorController {

	@Autowired
	private RegistradorService registradorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Registrador> listar(){
		return registradorService.consultarRegistrador();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Registrador> criarRegistrador(@Valid @RequestBody RegistradorDTO registradorDTO, HttpServletResponse response) {
		Registrador registrador=new Registrador();
		BeanUtils.copyProperties(registradorDTO, registrador);
		Registrador registradorSalvo=registradorService.criarRegistrador(registrador);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, registradorSalvo.getIdRegistrador()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registradorSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Registrador> alterarRegistrador(@PathVariable Long codigo,@Valid @RequestBody RegistradorDTO registradorDTO) {
		Registrador registrador=new Registrador();
		BeanUtils.copyProperties(registradorDTO, registrador);
		Registrador salvo=registradorService.alterarRegistrador(registrador, codigo);
		return ResponseEntity.ok(salvo);
		
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Registrador> detalharRegistrador(@PathVariable Long codigo) {
		Registrador registrador=registradorService.buscarRegistradorPeloCodigo(codigo);
		return ResponseEntity.ok(registrador);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirRegistrador(@PathVariable Long codigo) {
		registradorService.excluirRegistrador(codigo);
	
	}
}
