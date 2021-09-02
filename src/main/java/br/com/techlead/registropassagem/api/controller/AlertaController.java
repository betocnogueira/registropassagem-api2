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
import br.com.techlead.registropassagem.api.model.Alerta;
import br.com.techlead.registropassagem.api.model.AlertaMedicao;
import br.com.techlead.registropassagem.api.model.dto.AlertaDTO;
import br.com.techlead.registropassagem.api.repository.filter.AlertaFilter;
import br.com.techlead.registropassagem.api.service.AlertaService;
import io.swagger.annotations.Api;

@Api(value = "Alerta")
@RestController
@RequestMapping("/alerta")
public class AlertaController {

	@Autowired
	private AlertaService alertaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Alerta> listar(){
		return alertaService.consultarAlerta();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Alerta> criarAlerta(@Valid @RequestBody AlertaDTO alertaDto, HttpServletResponse response) {
		Alerta alerta=new Alerta();
		BeanUtils.copyProperties(alertaDto, alerta);
		Alerta alertaSalvo=alertaService.criarAlerta(alerta);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, alertaSalvo.getIdAlerta()));
		return ResponseEntity.status(HttpStatus.CREATED).body(alertaSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Alerta> alterarAlerta(@PathVariable Long codigo,@Valid @RequestBody AlertaDTO alertaDto) {
		Alerta alerta=new Alerta();
		BeanUtils.copyProperties(alertaDto, alerta);
		Alerta salvo=alertaService.alterarAlerta(alerta, codigo);
		return ResponseEntity.ok(salvo);
		
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Alerta> detalharAlerta(@PathVariable Long codigo) {
		Alerta alerta=alertaService.buscarAlertaPeloCodigo(codigo);
		return ResponseEntity.ok(alerta);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirAlerta(@PathVariable Long codigo) {
		alertaService.excluirAlerta(codigo);
		
	}
	
	@GetMapping("/medicao")
	public List<AlertaMedicao> listarAlertasMedicao(AlertaFilter filtro){
		return alertaService.consultarAlerta(filtro);
	}
}
