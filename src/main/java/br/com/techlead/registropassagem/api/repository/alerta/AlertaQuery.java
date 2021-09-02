package br.com.techlead.registropassagem.api.repository.alerta;

import java.util.List;

import br.com.techlead.registropassagem.api.model.AlertaMedicao;
import br.com.techlead.registropassagem.api.repository.filter.AlertaFilter;

public interface AlertaQuery {

	public List<AlertaMedicao> consultarAlertas(AlertaFilter filtro);
}
