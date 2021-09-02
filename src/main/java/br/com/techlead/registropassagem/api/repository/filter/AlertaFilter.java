package br.com.techlead.registropassagem.api.repository.filter;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.techlead.registropassagem.api.model.enums.TipoMedicao;
import br.com.techlead.registropassagem.api.model.enums.TipoNotificacao;

public class AlertaFilter {

	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoMedicao tipoMedicao;
	private Long idRegistrador;
	@Enumerated(EnumType.STRING)
	private TipoNotificacao tipoNotificacao;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoMedicao getTipoMedicao() {
		return tipoMedicao;
	}
	public void setTipoMedicao(TipoMedicao tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
	public Long getIdRegistrador() {
		return idRegistrador;
	}
	public void setIdRegistrador(Long idRegistrador) {
		this.idRegistrador = idRegistrador;
	}
	public TipoNotificacao getTipoNotificacao() {
		return tipoNotificacao;
	}
	public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
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
	
	
	
}
