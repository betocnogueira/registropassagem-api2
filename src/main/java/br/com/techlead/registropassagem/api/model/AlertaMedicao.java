package br.com.techlead.registropassagem.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = "POC_TECHLEAD")
public class AlertaMedicao {

	@Id
	private Long idAlertaMedicao;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataAlerta;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="IDMEDICAO")
	private Medicao medicao;
	@ManyToOne
	@JoinColumn(name="IDALERTA")
	private Alerta alerta;
	private String mensagem;
	private Long valor;
	
	public Long getIdAlertaMedicao() {
		return idAlertaMedicao;
	}
	public void setIdAlertaMedicao(Long idAlertaMedicao) {
		this.idAlertaMedicao = idAlertaMedicao;
	}
	
	public Date getDataAlerta() {
		return dataAlerta;
	}
	public void setDataAlerta(Date dataAlerta) {
		this.dataAlerta = dataAlerta;
	}
	public Medicao getMedicao() {
		return medicao;
	}
	public void setMedicao(Medicao medicao) {
		this.medicao = medicao;
	}
	public Alerta getAlerta() {
		return alerta;
	}
	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
}
