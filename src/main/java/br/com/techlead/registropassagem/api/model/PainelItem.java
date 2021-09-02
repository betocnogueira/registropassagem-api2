package br.com.techlead.registropassagem.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.techlead.registropassagem.api.model.dto.LinhaDTO;
import br.com.techlead.registropassagem.api.model.enums.TipoPainelItem;

@Entity
@Table(schema = "POC_TECHLEAD")
@SqlResultSetMapping(name = "LinhaDTO", classes = @ConstructorResult(targetClass = LinhaDTO.class,
columns = { @ColumnResult(name = "valor", type = Long.class),
        @ColumnResult(name = "registrador", type = String.class),
        @ColumnResult(name = "tipo", type = String.class),
        @ColumnResult(name = "tempo", type = String.class),
        @ColumnResult(name = "coorx", type = BigDecimal.class),
        @ColumnResult(name = "coory", type = BigDecimal.class),
        @ColumnResult(name = "dataAlerta", type = String.class),
        @ColumnResult(name = "mensagem", type = String.class)
        
}))
public class PainelItem {

	@Id
	@Column(name = "IDPAINELITEM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAINELITEMGENERATOR")
	@SequenceGenerator(name = "PAINELITEMGENERATOR", sequenceName = "POC_TECHLEAD.SEQ_IDPAINELITEM", initialValue = 1, allocationSize = 1)
	private Long idPainelItem;
	@NotNull
	private String titulo;
	private String descricao;
	@NotNull
	private Integer ordem;
	@NotNull
	private TipoPainelItem tipoPainelItem;
	private Integer tamanho;
	private Integer altura;
	@ManyToOne
	@JoinColumn(name="IDALERTA")
	private Alerta alerta;
	@ManyToOne
	@JoinColumn(name="IDPAINEL")
	@JsonIgnore
	private Painel painel;
	@ManyToOne
	@JoinColumn(name="IDINDICADOR")
	private Indicador indicador;
	public Long getIdPainelItem() {
		return idPainelItem;
	}
	public void setIdPainelItem(Long idPainelItem) {
		this.idPainelItem = idPainelItem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	public TipoPainelItem getTipoPainelItem() {
		return tipoPainelItem;
	}
	public void setTipoPainelItem(TipoPainelItem tipoPainelItem) {
		this.tipoPainelItem = tipoPainelItem;
	}
	
	public Painel getPainel() {
		return painel;
	}
	public void setPainel(Painel painel) {
		this.painel = painel;
	}
	public Integer getTamanho() {
		return tamanho;
	}
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
	public Integer getAltura() {
		return altura;
	}
	public void setAltura(Integer altura) {
		this.altura = altura;
	}
	public Alerta getAlerta() {
		return alerta;
	}
	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
	public Indicador getIndicador() {
		return indicador;
	}
	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
}
