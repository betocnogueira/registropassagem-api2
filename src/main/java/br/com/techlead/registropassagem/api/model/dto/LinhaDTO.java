package br.com.techlead.registropassagem.api.model.dto;

import java.math.BigDecimal;


public class LinhaDTO {

	private String registrador;
	private String tipo;
	private Long valor;
	private BigDecimal coorx;
	private BigDecimal coory;
	private String tempo;
	
	private String dataAlerta;
	private String mensagem;
	
	public LinhaDTO(Long valor, String registrador, String tipo, String tempo, BigDecimal coorx, BigDecimal coory,
			String dataAlerta, String mensagem) {
		super();
		this.valor = valor;
		this.registrador = registrador;
		this.tipo = tipo;
		this.tempo = tempo;
		this.coorx = coorx;
		this.coory = coory;
		this.dataAlerta = dataAlerta;
		this.mensagem = mensagem;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public String getRegistrador() {
		return registrador;
	}
	public void setRegistrador(String registrador) {
		this.registrador = registrador;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getCoorx() {
		return coorx;
	}
	public void setCoorx(BigDecimal coorx) {
		this.coorx = coorx;
	}
	public BigDecimal getCoory() {
		return coory;
	}
	public void setCoory(BigDecimal coory) {
		this.coory = coory;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public String getDataAlerta() {
		return dataAlerta;
	}
	public void setDataAlerta(String dataAlerta) {
		this.dataAlerta = dataAlerta;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
