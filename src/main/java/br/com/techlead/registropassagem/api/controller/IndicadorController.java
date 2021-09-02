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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.techlead.registropassagem.api.event.RecursoCriadoEvento;
import br.com.techlead.registropassagem.api.model.Indicador;
import br.com.techlead.registropassagem.api.model.dto.IndicadorDTO;
import br.com.techlead.registropassagem.api.service.IndicadorService;
import io.swagger.annotations.Api;


@Api(tags = "Indicadores")
@RestController
@RequestMapping("/indicador")
public class IndicadorController {

	@Autowired
	private IndicadorService indicadorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Indicador> listar(@RequestParam(required = false, defaultValue  = "") String descricao){
		return indicadorService.consultarIndicador(descricao);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Indicador> criarIndicador(@Valid @RequestBody IndicadorDTO indicadorDTO, HttpServletResponse response) {
		Indicador indicador=new Indicador();
		BeanUtils.copyProperties(indicadorDTO, indicador);
		Indicador indicadorSalvo=indicadorService.criarIndicador(indicador);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, indicadorSalvo.getIdIndicador()));
		return ResponseEntity.status(HttpStatus.CREATED).body(indicadorSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Indicador> alterarIndicador(@PathVariable Long codigo,@Valid @RequestBody IndicadorDTO indicadorDTO) {
		Indicador indicador=new Indicador();
		BeanUtils.copyProperties(indicadorDTO, indicador);
		Indicador salvo=indicadorService.alterarIndicador(indicador, codigo);
		return ResponseEntity.ok(salvo);
		
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Indicador> detalharIndicador(@PathVariable Long codigo) {
		Indicador indicador=indicadorService.buscarIndicadorPeloCodigo(codigo);
		return ResponseEntity.ok(indicador);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirIndicador(@PathVariable Long codigo) {
		indicadorService.excluirIndicador(codigo);
		
	}
}
