package br.com.techlead.registropassagem.api.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvento>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvento event) {
		HttpServletResponse response=event.getResponse();
		Long codigo=event.getCodigo();
		adicionarLocation(response, codigo);
				
	}

	private void adicionarLocation(HttpServletResponse response, Long codigo) {
		URI uri=ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri(); 
		response.setHeader("Location", uri.toASCIIString());
	}

	
}
