package br.com.techlead.registropassagem.api.model.dto;

public class PainelResumoDTO {

	private Long idPainel;
	private String descricao;
	private String titulo;
	private String image;
	
	
	public PainelResumoDTO(Long idPainel, String descricao, String titulo, String image) {
		super();
		this.idPainel = idPainel;
		this.descricao = descricao;
		this.titulo = titulo;
		this.image = image;
	}
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
