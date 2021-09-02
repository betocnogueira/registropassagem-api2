package br.com.techlead.registropassagem.api.repository.registropassagem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.techlead.registropassagem.api.model.RegistroPassagem;
import br.com.techlead.registropassagem.api.model.dto.RegistroPassagemResumoDTO;
import br.com.techlead.registropassagem.api.repository.filter.RegistroPassagemFilter;


public interface RegistroPassagemQuery {

	public Page<RegistroPassagemResumoDTO> resumir(RegistroPassagemFilter lancamentoFilter, Pageable pageable);
	public void enviarReceitaFederal(RegistroPassagem registro);
	public void processarBatch(Long tempo, Long registrador);
}
