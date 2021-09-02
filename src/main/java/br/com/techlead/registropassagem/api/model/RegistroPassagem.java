package br.com.techlead.registropassagem.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.techlead.registropassagem.api.model.enums.StatusRegistro;
import br.com.techlead.registropassagem.api.model.enums.TipoRegistro;

@Entity
@Table(schema = "POC_TECHLEAD", name  ="REGISTROPASSAGEM")
public class RegistroPassagem {

	@Id
	@Column(name = "IDREGISTROPASSAGEM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTROPASSAGEMGENERATOR")
	@SequenceGenerator(name = "REGISTROPASSAGEMGENERATOR", sequenceName = "POC_TECHLEAD.SEQ_IDREGISTROPASSAGEM", initialValue = 1, allocationSize = 1)
	private Long idRegistroPassagem;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoRegistro tipoRegistro;
	private String chaveMdfe;
	private String placa;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataCriacao;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date  dataEnvio;
	@Enumerated(EnumType.STRING)
	private StatusRegistro status;
	private String mensagemReceita;
	private String retornoMensagemReceita;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IDREGISTRADOR")
	private Registrador registrador;
	@OneToMany(mappedBy = "registroPassagem")
	@JsonIgnoreProperties("registroPassagem")
	private List<RegistroPassagemEvento> eventos;
	
	public Long getIdRegistroPassagem() {
		return idRegistroPassagem;
	}
	public void setIdRegistroPassagem(Long idRegistroPassagem) {
		this.idRegistroPassagem = idRegistroPassagem;
	}
	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getChaveMdfe() {
		return chaveMdfe;
	}
	public void setChaveMdfe(String chaveMdfe) {
		this.chaveMdfe = chaveMdfe;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public StatusRegistro getStatus() {
		return status;
	}
	public void setStatus(StatusRegistro status) {
		this.status = status;
	}
	public String getMensagemReceita() {
		return mensagemReceita;
	}
	public void setMensagemReceita(String mensagemReceita) {
		this.mensagemReceita = mensagemReceita;
	}
	public String getRetornoMensagemReceita() {
		return retornoMensagemReceita;
	}
	public void setRetornoMensagemReceita(String retornoMensagemReceita) {
		this.retornoMensagemReceita = retornoMensagemReceita;
	}
	public Registrador getRegistrador() {
		return registrador;
	}
	public void setRegistrador(Registrador registrador) {
		this.registrador = registrador;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public List<RegistroPassagemEvento> getEventos() {
		return eventos;
	}
	public void setEventos(List<RegistroPassagemEvento> eventos) {
		this.eventos = eventos;
	}
}
