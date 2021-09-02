package br.com.techlead.registropassagem.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.techlead.registropassagem.api.model.Integrador;
import br.com.techlead.registropassagem.api.repository.IntegradorRepository;


@Service
public class IntegradorService {

	@Autowired
	private IntegradorRepository integradorRepository;
	
	
	public List<Integrador> consultarIntegrador(){
		return integradorRepository.findByAtivoOrderByOrdemAsc(true);
	}
	
	public Integrador criarIntegrador(Integrador integrador) {
		integrador.setIdIntegrador(null);
		integrador.setAtivo(true);
		return integradorRepository.save(integrador);
		
	}
	
	public Integrador alterarIntegrador(Integrador integrador, Long codigo) {
		Integrador antigo=this.buscarIntegradorPeloCodigo(codigo);
		BeanUtils.copyProperties(integrador, antigo, "idIntegrador");
		return integradorRepository.save(antigo);
		
	}
	
	public void excluirIntegrador(Long codigo) {
		Integrador integrador=this.buscarIntegradorPeloCodigo(codigo);
		integrador.setAtivo(false);
		integradorRepository.save(integrador);
		
	}
	
	public Integrador buscarIntegradorPeloCodigo(Long codigo) {
		Integrador integrador=integradorRepository.findById(codigo).orElse(null);
		if(integrador==null ||integrador.getAtivo()==null || !integrador.getAtivo().booleanValue()) throw new EmptyResultDataAccessException(1);
		return integrador;
	}
	
	
	
	
	
	
}
