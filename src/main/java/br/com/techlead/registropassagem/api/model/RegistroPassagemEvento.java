package br.com.techlead.registropassagem.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.techlead.registropassagem.api.model.enums.StatusEvento;

@Entity
@Table(schema = "POC_TECHLEAD", name = "REGISTROPASSAGEMEVENTO")
public class RegistroPassagemEvento {

	@Id
	@Column(name = "IDREGPASSEVENTO")
	private Long idRegistroPassagemEvento;
	@Column(name = "DATAEXECUCAO")
	private LocalDate dataExecucao;
	@Enumerated(EnumType.STRING)
	private StatusEvento status;
	@Column(name = "MENSAGEMENVIO")
	private String mensagemEnvio;
	@Column(name = "MENSAGEMRETORNO")
	private String mensagemRetorno;
	@ManyToOne
	@JoinColumn(name="IDREGISTROPASSAGEM")
	private RegistroPassagem registroPassagem;
	@ManyToOne
	@JoinColumn(name="IDINTEGRADOR")
	private Integrador integrador;
	
	public Long getIdRegistroPassagemEvento() {
		return idRegistroPassagemEvento;
	}
	public void setIdRegistroPassagemEvento(Long idRegistroPassagemEvento) {
		this.idRegistroPassagemEvento = idRegistroPassagemEvento;
	}
	
	public StatusEvento getStatus() {
		return status;
	}
	public void setStatus(StatusEvento status) {
		this.status = status;
	}
	public String getMensagemEnvio() {
		return mensagemEnvio;
	}
	public void setMensagemEnvio(String mensagemEnvio) {
		this.mensagemEnvio = mensagemEnvio;
	}
	public String getMensagemRetorno() {
		return mensagemRetorno;
	}
	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}
	public RegistroPassagem getRegistroPassagem() {
		return registroPassagem;
	}
	public void setRegistroPassagem(RegistroPassagem registroPassagem) {
		this.registroPassagem = registroPassagem;
	}
	public Integrador getIntegrador() {
		return integrador;
	}
	public void setIntegrador(Integrador integrador) {
		this.integrador = integrador;
	}
	public LocalDate getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(LocalDate dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
}
