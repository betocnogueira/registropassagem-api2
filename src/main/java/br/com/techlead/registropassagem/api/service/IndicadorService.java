package br.com.techlead.registropassagem.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.techlead.registropassagem.api.model.Indicador;
import br.com.techlead.registropassagem.api.repository.IndicadorRepository;


@Service
public class IndicadorService {

	@Autowired
	private IndicadorRepository indicadorRepository;
	
	
	public List<Indicador> consultarIndicador(String descricao){
		return indicadorRepository.findByAtivoAndDescricaoIgnoreCaseContaining(true, descricao);
	}
	
	public Indicador criarIndicador(Indicador indicador) {
		indicador.setIdIndicador(null);
		indicador.setAtivo(true);
		return indicadorRepository.save(indicador);
		
	}
	
	public Indicador alterarIndicador(Indicador indicador, Long codigo) {
		Indicador antigo=this.buscarIndicadorPeloCodigo(codigo);
		BeanUtils.copyProperties(indicador, antigo, "idIndicador");
		indicadorRepository.save(antigo);
		return antigo;
	}
	
	public void excluirIndicador(Long codigo) {
		Indicador indicador=this.buscarIndicadorPeloCodigo(codigo);
		indicador.setAtivo(false);
		indicadorRepository.save(indicador);
		
	}
	
	public Indicador buscarIndicadorPeloCodigo(Long codigo) {
		Indicador indicador=indicadorRepository.findById(codigo).orElse(null);
		if(indicador==null ||indicador.getAtivo()==null || !indicador.getAtivo().booleanValue()) throw new EmptyResultDataAccessException(1);
		return indicador;
	}
	
	
	
	
	
	
	
}
