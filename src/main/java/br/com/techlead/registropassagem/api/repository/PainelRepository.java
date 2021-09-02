package br.com.techlead.registropassagem.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlead.registropassagem.api.model.Painel;
import br.com.techlead.registropassagem.api.repository.painel.PainelQuery;

public interface PainelRepository extends JpaRepository<Painel, Long>, PainelQuery{
	public List<Painel> findByAtivo(boolean ativo);
}
