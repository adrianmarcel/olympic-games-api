package br.com.adrian.olympicgames.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.adrian.olympicgames.api.entities.Competicao;
import br.com.adrian.olympicgames.api.services.CompeticaoService;

@RestController
@RequestMapping(value = "olympicgames/v1/competicoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompeticaoController {
	
	@Autowired
	private CompeticaoService competicaoService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<Competicao> findOne(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok(competicaoService.findOne(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Competicao>> findAll(@RequestParam(name = "modalidade", defaultValue = "") String modalidade) throws Exception {
		return ResponseEntity.ok(competicaoService.findAll(modalidade.toUpperCase(), new Sort(Sort.Direction.ASC, "dataInicio")));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Competicao> add(@RequestBody @Valid Competicao request) throws ConstraintViolationException, Exception {
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + competicaoService.save(request))
				                                  .buildAndExpand().toUri();

		return ResponseEntity.created(location).build();
	}
}