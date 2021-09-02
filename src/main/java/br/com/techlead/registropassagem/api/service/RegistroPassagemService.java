package br.com.techlead.registropassagem.api.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.techlead.registropassagem.api.model.RegistroPassagem;
import br.com.techlead.registropassagem.api.model.dto.RegistroPassagemResumoDTO;
import br.com.techlead.registropassagem.api.model.enums.StatusRegistro;
import br.com.techlead.registropassagem.api.repository.RegistroPassagemRepository;
import br.com.techlead.registropassagem.api.repository.filter.RegistroPassagemFilter;

@Service
public class RegistroPassagemService {

	@Autowired
	private RegistroPassagemRepository registroPassagemRepository;
	
	public Page<RegistroPassagemResumoDTO> consultarPassagem(RegistroPassagemFilter filtro, Pageable page){
		return registroPassagemRepository.resumir(filtro, page);
	}
	
	public RegistroPassagem detalharRegistroPassagem(Long codigo) {
		RegistroPassagem  retorno=registroPassagemRepository.findById(codigo).orElse(null);
		if(retorno==null) throw new EmptyResultDataAccessException(1);
		return retorno;
	}
	@Transactional
	public RegistroPassagem salvarRegistroPassagem(RegistroPassagem registro) {
		registro.setIdRegistroPassagem(null);
		registro.setStatus(StatusRegistro.GRAVADO);
		registro.setDataEnvio(null);
		if(registro.getDataCriacao()==null)
			registro.setDataCriacao(new Date());
			
		RegistroPassagem retorno=registroPassagemRepository.save(registro);
		registroPassagemRepository.enviarReceitaFederal(retorno);
		return retorno;
		
	}
	
	public void processarBatch(Long codigo, Long idRegistrador) {
		registroPassagemRepository.processarBatch(codigo, idRegistrador);
	}
}
