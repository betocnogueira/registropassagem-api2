package br.com.techlead.registropassagem.api.model.dto;

import java.util.List;

public class TabelaDTO {

	private boolean registrador;
	private boolean tipo;
	private boolean tempo;
	
	private List<LinhaDTO> linhas;
	
	public List<LinhaDTO> getLinhas() {
		return linhas;
	}
	public void setLinhas(List<LinhaDTO> linhas) {
		this.linhas = linhas;
	}
	public boolean isRegistrador() {
		return registrador;
	}
	public void setRegistrador(boolean registrador) {
		this.registrador = registrador;
	}
	public boolean isTipo() {
		return tipo;
	}
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	public boolean isTempo() {
		return tempo;
	}
	public void setTempo(boolean tempo) {
		this.tempo = tempo;
	}
	
}
