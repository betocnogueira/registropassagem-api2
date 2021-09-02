package br.com.techlead.registropassagem.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlead.registropassagem.api.model.Alerta;
import br.com.techlead.registropassagem.api.repository.alerta.AlertaQuery;

public interface AlertaRepository extends JpaRepository<Alerta, Long>, AlertaQuery
{
	public List<Alerta> findByAtivoOrderByDescricaoAsc(boolean ativo);
}
