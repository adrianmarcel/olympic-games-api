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

import br.com.adrian.olympicgames.api.entities.Pais;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PaisRepositoryTest {
	
	@Autowired
	private PaisRepository paisRepository;
	
	private static final String PAIS = "França";
	
	@Before
	public void setUp() throws Exception {
		Pais pais = new Pais();
		pais.setDescricao(PAIS);
		
		this.paisRepository.save(pais);
	}
	
	@After
	public final void tearDown() {
		this.paisRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorDescricao() {
		Pais pais = this.paisRepository.findByDescricao(PAIS);
		
		assertEquals(PAIS, pais.getDescricao());
	}
}