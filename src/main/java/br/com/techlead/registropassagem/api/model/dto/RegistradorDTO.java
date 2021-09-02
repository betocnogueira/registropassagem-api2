package br.com.techlead.registropassagem.api.model.dto;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import br.com.techlead.registropassagem.api.model.enums.TipoRegistrador;

public class RegistradorDTO {

	private Long idRegistrador;
	@NotNull
	private String descricao;
	private Boolean ativo;
	private BigDecimal coorX;
	private BigDecimal coorY;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoRegistrador tipo;
	public Long getIdRegistrador() {
		return idRegistrador;
	}
	public void setIdRegistrador(Long idRegistrador) {
		this.idRegistrador = idRegistrador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public BigDecimal getCoorX() {
		return coorX;
	}
	public void setCoorX(BigDecimal coorX) {
		this.coorX = coorX;
	}
	public BigDecimal getCoorY() {
		return coorY;
	}
	public void setCoorY(BigDecimal coorY) {
		this.coorY = coorY;
	}
	public TipoRegistrador getTipo() {
		return tipo;
	}
	public void setTipo(TipoRegistrador tipo) {
		this.tipo = tipo;
	}
}
