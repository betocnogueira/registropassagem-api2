package br.com.techlead.registropassagem.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlead.registropassagem.api.model.RegistroPassagem;
import br.com.techlead.registropassagem.api.repository.registropassagem.RegistroPassagemQuery;

public interface RegistroPassagemRepository extends JpaRepository<RegistroPassagem, Long>, RegistroPassagemQuery{
	
}
