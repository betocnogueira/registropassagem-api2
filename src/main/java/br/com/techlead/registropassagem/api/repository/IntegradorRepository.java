package br.com.techlead.registropassagem.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlead.registropassagem.api.model.Integrador;

public interface IntegradorRepository extends JpaRepository<Integrador, Long>{
	public List<Integrador> findByAtivoOrderByOrdemAsc(boolean ativo);
}
