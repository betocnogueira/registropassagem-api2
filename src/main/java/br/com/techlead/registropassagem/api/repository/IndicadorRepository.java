package br.com.techlead.registropassagem.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlead.registropassagem.api.model.Indicador;

public interface IndicadorRepository extends JpaRepository<Indicador, Long>{
	public List<Indicador> findByAtivoAndDescricaoIgnoreCaseContaining(boolean ativo, String descricao);
}
