package br.com.techlead.registropassagem.api.repository.painel;

import java.util.List;

import br.com.techlead.registropassagem.api.model.dto.LinhaDTO;
import br.com.techlead.registropassagem.api.model.dto.PainelResumoDTO;


public interface PainelQuery {

	public List<PainelResumoDTO> consultarPainel();
	
	public List<LinhaDTO> carregarTabela(Long idIndicadorAlerta, boolean tipoIndicador) ; 
}
