package br.com.adrian.olympicgames.api.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import br.com.adrian.olympicgames.api.entities.Competicao;
import javassist.NotFoundException;

public interface CompeticaoService {

	Competicao findOne(Long id) throws Exception;
	
	List<Competicao> findAll(String parametro, Sort sort) throws Exception, NotFoundException;
	
	Long save(Competicao competicao) throws Exception, NotFoundException;
}
