package br.com.adrian.olympicgames.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.adrian.olympicgames.api.entities.Modalidade;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ModalidadeRepositoryTest {
	
	@Autowired
	private ModalidadeRepository modalidadeRepository;
	
	private static final String MODALIDADE = "Natação";
	
	@Before
	public void setUp() throws Exception {
		Modalidade modalidade = new Modalidade();
		modalidade.setDescricao(MODALIDADE);
		
		this.modalidadeRepository.save(modalidade);
	}
	
	@After
	public final void tearDown() {
		this.modalidadeRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorDescricao() {
		Modalidade modalidade = this.modalidadeRepository.findByDescricao(MODALIDADE);
		
		assertEquals(MODALIDADE, modalidade.getDescricao());
	}
}