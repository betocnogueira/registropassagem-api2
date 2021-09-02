package br.com.techlead.registropassagem.api.repository.filter;

import java.util.Date;

public class AlertaMedicaoFilter {

	private Date dataInicio;
	private Date dataFim;
	private Boolean global;
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Boolean getGlobal() {
		return global;
	}
	public void setGlobal(Boolean global) {
		this.global = global;
	}
	
	
}
