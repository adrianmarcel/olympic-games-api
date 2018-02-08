package br.com.adrian.olympicgames.api.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.adrian.olympicgames.api.entities.Competicao;
import br.com.adrian.olympicgames.api.entities.Local;
import br.com.adrian.olympicgames.api.entities.Modalidade;
import br.com.adrian.olympicgames.api.entities.Pais;
import br.com.adrian.olympicgames.api.enums.Etapa;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompeticaoRepositoryTest {

	@Autowired
	private CompeticaoRepository competicaoRepository;
	
	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private ModalidadeRepository modalidadeRepository;
	
	@Autowired
	private PaisRepository paisRepository;
	
	private Long localId;
	private Long modalidadeId;
	private Long pais1Id;
	private Long pais2Id;
	
	private static final String LOCAL = "França";
	private static final String MODALIDADE = "Natação";
	private static final String PAIS1 = "Brasil";
	private static final String PAIS2 = "Argentina";
	
	@Before
	public void setUp() throws Exception {
		Modalidade modalidade = this.modalidadeRepository.save(obterDadosModalidade());
		this.setModalidadeId(modalidade.getId());
		
		Local local = this.localRepository.save(obterDadosLocal());
		this.setLocalId(local.getId());
		
		Pais pais1 = this.paisRepository.save(obterDadosPais(1));
		this.setPais1Id(pais1.getId());
		
		Pais pais2 = this.paisRepository.save(obterDadosPais(2));
		this.setPais2Id(pais2.getId());
		
		this.competicaoRepository.save(obterDadosCompeticao(obterDadosLocal(),
				                                            obterDadosModalidade(),
				                                            obterDadosPais(1),
				                                            obterDadosPais(2)));
	}
	
	@After
	public final void tearDown() {
		this.competicaoRepository.deleteAll();
	}

	//@Test
	public void testBuscarCompeticoesPorModalidade() {
		Optional<Competicao> competicao = this.competicaoRepository.findByModalidadeIdOrLocalId(this.getModalidadeId(), this.getLocalId(), obterDataInicio(), obterDataTermino());
	
		System.out.println("Competicao" + competicao);
	}
	
	@Test
	public void testBuscarQuantidadeCompeticoes() {
		Integer qtdCompeticoes = this.competicaoRepository.findByLocalIdOrDataInicio(this.getLocalId(), obterDataInicio());
	
		System.out.println("Quantidade de competições: " + qtdCompeticoes);
	}
	
	public Local obterDadosLocal() {
		Local local = new Local();
		local.setDescricao(LOCAL);
		
		return local;
	}
	
	public Modalidade obterDadosModalidade() {
		Modalidade modalidade = new Modalidade();
		modalidade.setDescricao(MODALIDADE);
		
		return modalidade;
	}
	
	public Pais obterDadosPais(Integer numeroPais) {
		Pais pais = new Pais();
		
		if (numeroPais == 1) {
			pais.setDescricao(PAIS1);
		} else if (numeroPais == 2) {
			pais.setDescricao(PAIS2);
		}
		
		return pais;
	}
	
	public Competicao obterDadosCompeticao(Local local, Modalidade modalidade, Pais pais1, Pais pais2) {
		Competicao competicao = new Competicao();
		
		competicao.setLocal(local);
		competicao.setModalidade(modalidade);
		competicao.setPais1(pais1);
		competicao.setPais2(pais2);
		competicao.setEtapa(Etapa.FINAL);
		competicao.setDataInicio(new Date());
		competicao.setDataTermino(new Date());
		
		return competicao;
	}
	
	public Date obterDataInicio() {
		String dataInicio = "07/02/2018";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dataInicioFormatada = new Date();
		try {
			dataInicioFormatada = sdf.parse(dataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dataInicioFormatada;
	}
	
	public Date obterDataTermino() {
		String dataTermino = "09/02/2018";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dataTerminoFormatada = new Date();
		try {
			dataTerminoFormatada = sdf.parse(dataTermino);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dataTerminoFormatada;
	}

	public Long getLocalId() {
		return localId;
	}

	public void setLocalId(Long localId) {
		this.localId = localId;
	}

	public Long getModalidadeId() {
		return modalidadeId;
	}

	public void setModalidadeId(Long modalidadeId) {
		this.modalidadeId = modalidadeId;
	}

	public Long getPais1Id() {
		return pais1Id;
	}

	public void setPais1Id(Long pais1Id) {
		this.pais1Id = pais1Id;
	}

	public Long getPais2Id() {
		return pais2Id;
	}

	public void setPais2Id(Long pais2Id) {
		this.pais2Id = pais2Id;
	}
}