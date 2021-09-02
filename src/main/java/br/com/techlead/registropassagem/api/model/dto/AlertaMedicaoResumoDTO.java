package br.com.techlead.registropassagem.api.model.dto;

import java.util.Date;

import br.com.techlead.registropassagem.api.model.enums.TipoNotificacao;

public class AlertaMedicaoResumoDTO {

	private String nomeAlerta;
	private String nomeOrigem;
	private String tipoOrigem;
	private Date dataAlerta;
	private Boolean global;
	private Long idOrigem;
	private Long idAlerta;
	private TipoNotificacao tipoNotificacao;
	private String destinatarioAlerta;
	public String getNomeAlerta() {
		return nomeAlerta;
	}
	public void setNomeAlerta(String nomeAlerta) {
		this.nomeAlerta = nomeAlerta;
	}
	public String getNomeOrigem() {
		return nomeOrigem;
	}
	public void setNomeOrigem(String nomeOrigem) {
		this.nomeOrigem = nomeOrigem;
	}
	public String getTipoOrigem() {
		return tipoOrigem;
	}
	public void setTipoOrigem(String tipoOrigem) {
		this.tipoOrigem = tipoOrigem;
	}
	public Date getDataAlerta() {
		return dataAlerta;
	}
	public void setDataAlerta(Date dataAlerta) {
		this.dataAlerta = dataAlerta;
	}
	public Boolean getGlobal() {
		return global;
	}
	public void setGlobal(Boolean global) {
		this.global = global;
	}
	public Long getIdOrigem() {
		return idOrigem;
	}
	public void setIdOrigem(Long idOrigem) {
		this.idOrigem = idOrigem;
	}
	public Long getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}
	public TipoNotificacao getTipoNotificacao() {
		return tipoNotificacao;
	}
	public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
	}
	public String getDestinatarioAlerta() {
		return destinatarioAlerta;
	}
	public void setDestinatarioAlerta(String destinatarioAlerta) {
		this.destinatarioAlerta = destinatarioAlerta;
	}
	

	
}
