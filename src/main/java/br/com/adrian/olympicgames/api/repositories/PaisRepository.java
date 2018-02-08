package br.com.adrian.olympicgames.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrian.olympicgames.api.entities.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long>{
	
	@Transactional(readOnly = true)
	Pais findByDescricao(String descricao);
}