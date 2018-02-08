package br.com.adrian.olympicgames.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrian.olympicgames.api.entities.Modalidade;

public interface ModalidadeRepository extends JpaRepository<Modalidade, Long>{
	
	@Transactional(readOnly = true)
	Modalidade findByDescricao(String descricao);
}