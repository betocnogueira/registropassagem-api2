package br.com.techlead.registropassagem.api.repository.filter;

public class PainelFilter {

	private String descricao;
	private Integer tempoAtualizacao;
	private Integer unidadeDeTempo;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getTempoAtualizacao() {
		return tempoAtualizacao;
	}
	public void setTempoAtualizacao(Integer tempoAtualizacao) {
		this.tempoAtualizacao = tempoAtualizacao;
	}
	public Integer getUnidadeDeTempo() {
		return unidadeDeTempo;
	}
	public void setUnidadeDeTempo(Integer unidadeDeTempo) {
		this.unidadeDeTempo = unidadeDeTempo;
	}
}
