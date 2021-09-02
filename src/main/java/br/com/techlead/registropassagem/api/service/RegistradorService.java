package br.com.techlead.registropassagem.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.techlead.registropassagem.api.model.Registrador;
import br.com.techlead.registropassagem.api.repository.RegistradorRepository;

@Service
public class RegistradorService {

	@Autowired
	private RegistradorRepository registradorRepository;
	
	
	public List<Registrador> consultarRegistrador(){
		return registradorRepository.findByAtivo(true);
	}
	
	public Registrador criarRegistrador(Registrador registrador) {
		registrador.setIdRegistrador(null);
		registrador.setAtivo(true);
		return registradorRepository.save(registrador);
		
	}
	
	public Registrador alterarRegistrador(Registrador registrador, Long codigo) {
		Registrador antigo=this.buscarRegistradorPeloCodigo(codigo);
		BeanUtils.copyProperties(registrador, antigo, "idRegistrador");
		registradorRepository.save(antigo);
		return antigo;
	}
	
	public void excluirRegistrador(Long codigo) {
		Registrador registrador=this.buscarRegistradorPeloCodigo(codigo);
		registrador.setAtivo(false);
		registradorRepository.save(registrador);
		
	}
	
	public Registrador buscarRegistradorPeloCodigo(Long codigo) {
		Registrador registrador=registradorRepository.findById(codigo).orElse(null);
		if(registrador==null ||registrador.getAtivo()==null || !(registrador.getAtivo().booleanValue())) throw new EmptyResultDataAccessException(1);
		return registrador;
	}
	
	
	
	
	
	
}
