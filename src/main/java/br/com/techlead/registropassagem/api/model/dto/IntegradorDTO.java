package br.com.techlead.registropassagem.api.model.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import br.com.techlead.registropassagem.api.model.enums.TipoIntegrador;

public class IntegradorDTO {

	private Long idIntegrador;
	@NotNull
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoIntegrador tipo;
	@NotNull
	private Integer ordem;
	private String endereco;
	private String mensagem;
	@Column(name = "CONTINUAEMERRO")
	private boolean continuaEmErro;
	private String cabecalho;
	private Boolean ativo;
	public Long getIdIntegrador() {
		return idIntegrador;
	}
	public void setIdIntegrador(Long idIntegrador) {
		this.idIntegrador = idIntegrador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoIntegrador getTipo() {
		return tipo;
	}
	public void setTipo(TipoIntegrador tipo) {
		this.tipo = tipo;
	}
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public boolean isContinuaEmErro() {
		return continuaEmErro;
	}
	public void setContinuaEmErro(boolean continuaEmErro) {
		this.continuaEmErro = continuaEmErro;
	}
	public String getCabecalho() {
		return cabecalho;
	}
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
