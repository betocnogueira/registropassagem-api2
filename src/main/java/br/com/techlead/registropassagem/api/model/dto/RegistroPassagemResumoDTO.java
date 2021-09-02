package br.com.techlead.registropassagem.api.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.techlead.registropassagem.api.model.enums.StatusRegistro;
import br.com.techlead.registropassagem.api.model.enums.TipoRegistrador;
import br.com.techlead.registropassagem.api.model.enums.TipoRegistro;
import io.swagger.annotations.ApiModel;

@ApiModel(value="Registro de passagem resumido",  description = "Representa o registro de passagem realizado de forma resumida")
public class RegistroPassagemResumoDTO {

	private Long idRegistroPassagem;
	private String placa;
	private String chaveDfe;
	private String nomeOrigem;
	private TipoRegistrador tipoOrigem;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date  dataRegistro;
	private StatusRegistro status;
	private TipoRegistro tipoRegistro;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date  dataRecebimento;
	
	public RegistroPassagemResumoDTO(Long idRegistroPassagem, TipoRegistro tipoRegistro, String placa, String chaveDfe,
			Date  dataRecebimento, Date  dataRegistro, StatusRegistro status, TipoRegistrador tipoOrigem,
			String nomeOrigem) {
		super();
		this.idRegistroPassagem = idRegistroPassagem;
		this.tipoRegistro = tipoRegistro;
		this.placa = placa;
		this.chaveDfe = chaveDfe;
		this.dataRecebimento = dataRecebimento;
		this.dataRegistro = dataRegistro;
		this.status = status;
		this.tipoOrigem = tipoOrigem;
		this.nomeOrigem = nomeOrigem;
	}
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
	public String getNomeOrigem() {
		return nomeOrigem;
	}
	public void setNomeAntena(String nomeOrigem) {
		this.nomeOrigem = nomeOrigem;
	}
	
	public StatusRegistro getStatus() {
		return status;
	}
	public void setStatus(StatusRegistro status) {
		this.status = status;
	}
	public TipoRegistrador getTipoOrigem() {
		return tipoOrigem;
	}
	public void setTipoOrigem(TipoRegistrador tipoOrigem) {
		this.tipoOrigem = tipoOrigem;
	}
	public void setNomeOrigem(String nomeOrigem) {
		this.nomeOrigem = nomeOrigem;
	}
	public Date  getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date  dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public Date  getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(Date  dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	public Long getIdRegistroPassagem() {
		return idRegistroPassagem;
	}
	public void setIdRegistroPassagem(Long idRegistroPassagem) {
		this.idRegistroPassagem = idRegistroPassagem;
	}
	
	
}
