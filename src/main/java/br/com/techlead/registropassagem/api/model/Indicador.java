package br.com.techlead.registropassagem.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "POC_TECHLEAD")
public class Indicador {

	@Id
	@Column(name = "IDINDICADOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INDICADORGENERATOR")
	@SequenceGenerator(name = "INDICADORGENERATOR", sequenceName = "POC_TECHLEAD.SEQ_IDINDICADOR", initialValue = 1, allocationSize = 1)
	private Long idIndicador;
	private String descricao;
	private Integer tipoAmostra;
	private String tipoIndicador;
	private String tipoIndicadorDesc;
	private String agrupamento;
	private String tipoRegistrador;
	@ManyToOne
	@JoinColumn(name="IDREGISTRADOR")
	private Registrador registrador;
	private Integer quantidadeTempo;
	private String unidadeTempo;
	private Integer meta;
	private Boolean ativo;
	public Long getIdIndicador() {
		return idIndicador;
	}
	public void setIdIndicador(Long idIndicador) {
		this.idIndicador = idIndicador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getTipoAmostra() {
		return tipoAmostra;
	}
	public void setTipoAmostra(Integer tipoAmostra) {
		this.tipoAmostra = tipoAmostra;
	}
	public String getTipoIndicador() {
		return tipoIndicador;
	}
	public void setTipoIndicador(String tipoIndicador) {
		this.tipoIndicador = tipoIndicador;
	}
	public String getTipoIndicadorDesc() {
		return tipoIndicadorDesc;
	}
	public void setTipoIndicadorDesc(String tipoIndicadorDesc) {
		this.tipoIndicadorDesc = tipoIndicadorDesc;
	}
	public String getAgrupamento() {
		return agrupamento;
	}
	public void setAgrupamento(String agrupamento) {
		this.agrupamento = agrupamento;
	}
	public String getTipoRegistrador() {
		return tipoRegistrador;
	}
	public void setTipoRegistrador(String tipoRegistrador) {
		this.tipoRegistrador = tipoRegistrador;
	}
	public Registrador getRegistrador() {
		return registrador;
	}
	public void setRegistrador(Registrador registrador) {
		this.registrador = registrador;
	}
	public Integer getQuantidadeTempo() {
		return quantidadeTempo;
	}
	public void setQuantidadeTempo(Integer quantidadeTempo) {
		this.quantidadeTempo = quantidadeTempo;
	}
	public String getUnidadeTempo() {
		return unidadeTempo;
	}
	public void setUnidadeTempo(String unidadeTempo) {
		this.unidadeTempo = unidadeTempo;
	}
	public Integer getMeta() {
		return meta;
	}
	public void setMeta(Integer meta) {
		this.meta = meta;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
