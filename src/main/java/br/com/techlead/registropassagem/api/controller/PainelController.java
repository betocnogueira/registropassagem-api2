package br.com.techlead.registropassagem.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.techlead.registropassagem.api.model.Painel;
import br.com.techlead.registropassagem.api.model.dto.ApresentacaoPainelDTO;
import br.com.techlead.registropassagem.api.model.dto.PainelResumoDTO;
import br.com.techlead.registropassagem.api.service.PainelService;
import io.swagger.annotations.Api;

@Api(tags = "Paineis")
@RestController
@RequestMapping("/painel")
public class PainelController {

	@Autowired
	private PainelService painelService;
	
	@GetMapping
	public List<PainelResumoDTO> listarPaineis(){
		return painelService.consultarPaineis();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Painel> criarPainel(@Valid @RequestBody Painel painel, HttpServletResponse response) {
		Painel retorno=painelService.criarPainel(painel);
		return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Painel> alterarPainel(@PathVariable Long codigo, @Valid @RequestBody Painel painel) {
		Painel retorno=painelService.alterarPainel(painel, codigo);
		return ResponseEntity.ok(retorno);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Painel> detalharPainel(@PathVariable Long codigo) {
		Painel retorno= painelService.buscarPainelPeloCodigo(codigo);
		return ResponseEntity.ok(retorno);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removerPainel(@PathVariable Long codigo) {
		painelService.excluirPainel(codigo);
	}
	
	
	@GetMapping("/painelitem/{codigo}")
	public ResponseEntity<ApresentacaoPainelDTO> carregarItemDashboard(@PathVariable Long codigo) {
		ApresentacaoPainelDTO retorno= painelService.buscarItemDashBoard(codigo);
		return ResponseEntity.ok(retorno);
	}
	
}
