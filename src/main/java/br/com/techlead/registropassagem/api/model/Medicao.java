package br.com.techlead.registropassagem.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "POC_TECHLEAD")
public class Medicao {

	@Id
	private Long idMedicao;
	private Date dataMedicao;
	public Long getIdMedicao() {
		return idMedicao;
	}
	public void setIdMedicao(Long idMedicao) {
		this.idMedicao = idMedicao;
	}
	public Date getDataMedicao() {
		return dataMedicao;
	}
	public void setDataMedicao(Date dataMedicao) {
		this.dataMedicao = dataMedicao;
	}
}
