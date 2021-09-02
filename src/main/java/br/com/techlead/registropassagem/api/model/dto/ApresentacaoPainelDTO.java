package br.com.techlead.registropassagem.api.model.dto;

import br.com.techlead.registropassagem.api.model.enums.TipoAmostra;

public class ApresentacaoPainelDTO {

	private TipoAmostra tipo;
	private ColunaDTO coluna;
	private TabelaDTO tabela;
	
	public TipoAmostra getTipo() {
		return tipo;
	}
	public void setTipo(TipoAmostra tipo) {
		this.tipo = tipo;
	}
	public ColunaDTO getColuna() {
		return coluna;
	}
	public void setColuna(ColunaDTO coluna) {
		this.coluna = coluna;
	}
	public TabelaDTO getTabela() {
		return tabela;
	}
	public void setTabela(TabelaDTO tabela) {
		this.tabela = tabela;
	}
	
	
}
