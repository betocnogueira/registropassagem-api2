package br.com.techlead.registropassagem.api.repository.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.techlead.registropassagem.api.model.enums.StatusRegistro;

public class RegistroPassagemFilter {

	private String placa;
	private String chaveDfe;
	private String nomeAntena;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
	private StatusRegistro status;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getChaveDfe() {
		return chaveDfe;
	}
	public void setChaveDfe(String chaveDfe) {
		this.chaveDfe = chaveDfe;
	}
	public String getNomeAntena() {
		return nomeAntena;
	}
	public void setNomeAntena(String nomeAntena) {
		this.nomeAntena = nomeAntena;
	}
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
	public StatusRegistro getStatus() {
		return status;
	}
	public void setStatus(StatusRegistro status) {
		this.status = status;
	}
}
