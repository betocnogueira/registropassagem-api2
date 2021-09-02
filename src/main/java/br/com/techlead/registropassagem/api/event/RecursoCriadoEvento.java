package br.com.techlead.registropassagem.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvento extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private transient HttpServletResponse response;
	private Long codigo;
	
	public RecursoCriadoEvento(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.codigo=codigo;
		this.response=response;
		
		
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	

}
