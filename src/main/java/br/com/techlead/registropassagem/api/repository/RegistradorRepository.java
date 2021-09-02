package br.com.techlead.registropassagem.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import br.com.techlead.registropassagem.api.model.Registrador;

public interface RegistradorRepository extends JpaRepository<Registrador, Long>{
	public List<Registrador> findByAtivo(boolean ativo);
}
