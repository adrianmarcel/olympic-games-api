package br.com.adrian.olympicgames.api.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.adrian.olympicgames.api.entities.Competicao;
import br.com.adrian.olympicgames.api.entities.Modalidade;
import br.com.adrian.olympicgames.api.enums.Etapa;
import br.com.adrian.olympicgames.api.repositories.CompeticaoRepository;
import br.com.adrian.olympicgames.api.repositories.ModalidadeRepository;
import br.com.adrian.olympicgames.api.services.CompeticaoService;
import javassist.NotFoundException;

@Service
public class CompeticaoServiceImpl implements CompeticaoService {
	
	@Autowired
	private CompeticaoRepository competicaoRepository;
	
	@Autowired
	private ModalidadeRepository modalidadeRepository;
	
	public Competicao findOne(Long id) throws Exception, NotFoundException {
		Competicao competicao = competicaoRepository.findOne(id);
		
		if (competicao == null) {
			throw new NotFoundException("A busca por uma competição pelo ID: " + id + " não retornou resultados");
		}
		
		return competicao;
	}
	
	public List<Competicao> findAll(Sort sort) throws Exception, NotFoundException {
		List<Competicao> competicoes = new ArrayList<>();
		
		for (Competicao competicao : competicaoRepository.findAll(sort)) {
			competicoes.add(competicao);
		}
		
		return competicoes;
	}
	
	public List<Competicao> findAll(String descModalidade, Sort sort) throws Exception, NotFoundException {
			
		List<Competicao> competicoes = new ArrayList<>();
			
		if (!descModalidade.isEmpty()) {
			Modalidade modalidade = modalidadeRepository.findByDescricao(descModalidade);
			
			for (Competicao competicao : competicaoRepository.findByModalidade(modalidade, sort)) {
				competicoes.add(competicao);
			}
		} else {
			for (Competicao competicao : competicaoRepository.findAll(sort)) {
				competicoes.add(competicao);
			}
		}
		
		return competicoes;
	}
	
	public Long save(Competicao competicao) throws Exception {
		
		try {
			this.validar(competicao);
		} catch (DataIntegrityViolationException dive) {
			dive.getMessage();
		} catch (Exception e) {
			e.getMessage();
		}
		
		return competicaoRepository.save(competicao).getId();
		
	}
	
	public void validar(Competicao competicao) throws Exception {
		
		long tempoMinimoCompeticao = new Long("30");
		long diferencaMinutos = (competicao.getDataTermino().getTime() - competicao.getDataInicio().getTime()) / (1000*60);
		DateFormat datetimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		
		// 1 - Validar competições no mesmo período
		Optional<Competicao> competicoesAgend = competicaoRepository.findByModalidadeIdOrLocalId(competicao.getModalidade().getId(),
				                                                                                   competicao.getLocal().getId(),
				                                                                                   competicao.getDataInicio(),
				                                                                                   competicao.getDataTermino());
		
		if (!Optional.empty().equals(competicoesAgend)) {			
			throw new DataIntegrityViolationException("A competição " + competicoesAgend.get().getModalidade().getDescricao()
					                                  + " já está agendada no período de: " + datetimeFormat.format(competicoesAgend.get().getDataInicio())
					                                  + " a " + datetimeFormat.format(competicoesAgend.get().getDataTermino()) + ". Por favor, agende outro período! ");
		}
		
		// 2 - Verificar etapas SEMIFINAL | FINAL
		if ((competicao.getPais1().getId() == competicao.getPais2().getId()) && (competicao.getEtapa() != Etapa.SEMIFINAL || competicao.getEtapa() != Etapa.FINAL)) {
			throw new DataIntegrityViolationException("Disputas de competições só são permitidas entre os mesmos países nas etapas SEMIFINAL e FINAL.");
		}
		
		// 3 - Verificar tempo mínimo de cada competição
		if (diferencaMinutos < tempoMinimoCompeticao) {
			throw new DataIntegrityViolationException("O período de uma competição deve ser de no mínimo 30 minutos.");
		}
		
		// 4 - Verificar quantidade de competições agendadas por dia.
		Integer qtdCompeticoes = competicaoRepository.findByLocalIdOrDataInicio(competicao.getLocal().getId(), competicao.getDataInicio());
		
		if (qtdCompeticoes >= 4) {
			throw new DataIntegrityViolationException("Não foi possível realizar o agendamento da sua competição!"
					                                  + " Já existem" + qtdCompeticoes + " competições agendadas no local: " + competicao.getLocal().getDescricao()
					                                  + " para o dia: " + date.format(competicao.getDataInicio()));
		}
	}
}