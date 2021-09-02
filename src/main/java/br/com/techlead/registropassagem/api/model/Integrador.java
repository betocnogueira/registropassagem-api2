package br.com.techlead.registropassagem.api.model;



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

import br.com.techlead.registropassagem.api.model.enums.TipoIntegrador;

@Entity
@Table(schema ="POC_TECHLEAD")
public class Integrador {

	@Id
	@Column(name = "IDINTEGRADOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INTEGRADORGENERATOR")
	@SequenceGenerator(name = "INTEGRADORGENERATOR", sequenceName = "POC_TECHLEAD.SEQ_IDINTEGRADOR", initialValue = 1, allocationSize = 1)
	private Long idIntegrador;
	@NotNull
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoIntegrador tipo;
	@NotNull
	private Integer ordem;
	private String endereco;
	private String mensagem;
	@Column(name = "CONTINUAEMERRO")
	private boolean continuaEmErro;
	private String cabecalho;
	private Boolean ativo;
	public Long getIdIntegrador() {
		return idIntegrador;
	}
	public void setIdIntegrador(Long idIntegrador) {
		this.idIntegrador = idIntegrador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoIntegrador getTipo() {
		return tipo;
	}
	public void setTipo(TipoIntegrador tipo) {
		this.tipo = tipo;
	}
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public boolean isContinuaEmErro() {
		return continuaEmErro;
	}
	public void setContinuaEmErro(boolean continuaEmErro) {
		this.continuaEmErro = continuaEmErro;
	}
	public String getCabecalho() {
		return cabecalho;
	}
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
