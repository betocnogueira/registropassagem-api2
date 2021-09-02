package br.com.techlead.registropassagem.api.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.techlead.registropassagem.api.model.PainelItem;

public class PainelDTO {
	
	private Long idPainel;
	private String descricao;
	@NotNull
	private String nome;
	private String image;
	private Boolean ativo;
	
	private List<PainelItem> itens;

	public Long getIdPainel() {
		return idPainel;
	}

	public void setIdPainel(Long idPainel) {
		this.idPainel = idPainel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<PainelItem> getItens() {
		return itens;
	}

	public void setItens(List<PainelItem> itens) {
		this.itens = itens;
	}
}
