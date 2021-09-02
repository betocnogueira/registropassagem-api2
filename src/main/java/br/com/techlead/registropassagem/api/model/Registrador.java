package br.com.techlead.registropassagem.api.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.techlead.registropassagem.api.model.enums.TipoRegistrador;

@Entity
@Table(schema ="POC_TECHLEAD")
public class Registrador {

	@Id
	@Column(name = "IDREGISTRADOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTRADORGENERATOR")
	@SequenceGenerator(name = "REGISTRADORGENERATOR", sequenceName = "POC_TECHLEAD.SEQ_IDREGISTRADOR", initialValue = 1, allocationSize = 1)
	private Long idRegistrador;
	@NotNull
	private String descricao;
	private Boolean ativo;
	private BigDecimal coorX;
	private BigDecimal coorY;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoRegistrador tipo;
	public Long getIdRegistrador() {
		return idRegistrador;
	}
	public void setIdRegistrador(Long idRegistrador) {
		this.idRegistrador = idRegistrador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public TipoRegistrador getTipo() {
		return tipo;
	}
	public void setTipo(TipoRegistrador tipo) {
		this.tipo = tipo;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public BigDecimal getCoorX() {
		return coorX;
	}
	public void setCoorX(BigDecimal coorX) {
		this.coorX = coorX;
	}
	public BigDecimal getCoorY() {
		return coorY;
	}
	public void setCoorY(BigDecimal coorY) {
		this.coorY = coorY;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idRegistrador);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registrador other = (Registrador) obj;
		return Objects.equals(idRegistrador, other.idRegistrador);
	}
}
