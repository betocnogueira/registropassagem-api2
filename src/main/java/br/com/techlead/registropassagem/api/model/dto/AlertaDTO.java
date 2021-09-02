package br.com.techlead.registropassagem.api.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.techlead.registropassagem.api.model.Registrador;
import br.com.techlead.registropassagem.api.model.enums.TipoMedicao;
import br.com.techlead.registropassagem.api.model.enums.TipoNotificacao;
import br.com.techlead.registropassagem.api.model.enums.UnidadeTempo;

public class AlertaDTO {

	private Long idAlerta;
	@NotNull
	private String descricao;
	private Boolean ativo=true;
	private Boolean global=false;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoMedicao tipoMedicao;
	@Enumerated(EnumType.STRING)
	private UnidadeTempo unidadeTempo;
	private Integer quantidadeTempo;
	private Integer quantidadeRegistro;
	@Enumerated(EnumType.STRING)
	private TipoNotificacao destinoAlerta;
	private String destinatario;
	@ManyToOne
	@JoinColumn(name="IDREGISTRADOR")
	private Registrador registrador;
	public Long getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
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
	public Boolean getGlobal() {
		return global;
	}
	public void setGlobal(Boolean global) {
		this.global = global;
	}
	public TipoMedicao getTipoMedicao() {
		return tipoMedicao;
	}
	public void setTipoMedicao(TipoMedicao tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
	public UnidadeTempo getUnidadeTempo() {
		return unidadeTempo;
	}
	public void setUnidadeTempo(UnidadeTempo unidadeTempo) {
		this.unidadeTempo = unidadeTempo;
	}
	public Integer getQuantidadeTempo() {
		return quantidadeTempo;
	}
	public void setQuantidadeTempo(Integer quantidadeTempo) {
		this.quantidadeTempo = quantidadeTempo;
	}
	public Integer getQuantidadeRegistro() {
		return quantidadeRegistro;
	}
	public void setQuantidadeRegistro(Integer quantidadeRegistro) {
		this.quantidadeRegistro = quantidadeRegistro;
	}
	public TipoNotificacao getDestinoAlerta() {
		return destinoAlerta;
	}
	public void setDestinoAlerta(TipoNotificacao destinoAlerta) {
		this.destinoAlerta = destinoAlerta;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public Registrador getRegistrador() {
		return registrador;
	}
	public void setRegistrador(Registrador registrador) {
		this.registrador = registrador;
	}
}
