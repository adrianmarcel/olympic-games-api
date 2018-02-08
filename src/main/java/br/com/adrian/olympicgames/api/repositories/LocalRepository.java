package br.com.adrian.olympicgames.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrian.olympicgames.api.entities.Local;

public interface LocalRepository extends JpaRepository<Local, Long>{

	@Transactional(readOnly = true)
	Local findByDescricao(String descricao);
}