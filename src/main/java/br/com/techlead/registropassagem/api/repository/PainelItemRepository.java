package br.com.techlead.registropassagem.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlead.registropassagem.api.model.PainelItem;

public interface PainelItemRepository extends JpaRepository<PainelItem, Long>{
	
}
