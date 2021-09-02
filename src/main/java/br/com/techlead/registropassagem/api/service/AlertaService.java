package br.com.techlead.registropassagem.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.techlead.registropassagem.api.model.Alerta;
import br.com.techlead.registropassagem.api.model.AlertaMedicao;
import br.com.techlead.registropassagem.api.repository.AlertaRepository;
import br.com.techlead.registropassagem.api.repository.filter.AlertaFilter;


@Service
public class AlertaService {

	@Autowired
	private AlertaRepository alertaRepository;
	
	
	public List<Alerta> consultarAlerta(){
		return alertaRepository.findByAtivoOrderByDescricaoAsc(true);
	}
	
	public Alerta criarAlerta(Alerta alerta) {
		alerta.setIdAlerta(null);
		alerta.setAtivo(true);
		return alertaRepository.save(alerta);
		
	}
	
	public Alerta alterarAlerta(Alerta alerta, Long codigo) {
		Alerta antigo=this.buscarAlertaPeloCodigo(codigo);
		BeanUtils.copyProperties(alerta, antigo, "idAlerta");
		return alertaRepository.save(antigo);
		
	}
	
	public void excluirAlerta(Long codigo) {
		Alerta alerta=this.buscarAlertaPeloCodigo(codigo);
		alerta.setAtivo(false);
		alertaRepository.save(alerta);
		
	}
	
	public Alerta buscarAlertaPeloCodigo(Long codigo) {
		Alerta alerta=alertaRepository.findById(codigo).orElse(null);
		if(alerta==null ||alerta.getAtivo()==null || !alerta.getAtivo().booleanValue()) throw new EmptyResultDataAccessException(1);
		return alerta;
	}
	
	
	public List<AlertaMedicao> consultarAlerta(AlertaFilter filtro){
		return alertaRepository.consultarAlertas(filtro);
	}
	
	
	
	
	
	
}
