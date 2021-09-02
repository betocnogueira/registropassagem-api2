package br.com.techlead.registropassagem.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "POC_TECHLEAD")
public class Painel {

	@Id
	@Column(name = "IDPAINEL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAINELGENERATOR")
	@SequenceGenerator(name = "PAINELGENERATOR", sequenceName = "POC_TECHLEAD.SEQ_IDPAINEL", initialValue = 1, allocationSize = 1)
	private Long idPainel;
	private String descricao;
	@NotNull
	private String nome;
	private String image;
	private Boolean ativo;
	@OneToMany(mappedBy = "painel", cascade = CascadeType.ALL)
	private List<PainelItem> itens;
	
	
	public Long getIdPainel() {
		return idPainel;
	}
	public void setIdPainel(Long idPainel) {
		this.idPainel = idPainel;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<PainelItem> getItens() {
		return itens;
	}
	public void setItens(List<PainelItem> itens) {
		this.itens = itens;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
