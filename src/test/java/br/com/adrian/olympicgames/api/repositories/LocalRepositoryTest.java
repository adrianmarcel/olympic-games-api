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

import br.com.adrian.olympicgames.api.entities.Local;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LocalRepositoryTest {
	
	@Autowired
	private LocalRepository localRepository;
	
	private static final String LOCAL = "Zaha Hadid";
	
	@Before
	public void setUp() throws Exception {
		Local local = new Local();
		local.setDescricao(LOCAL);
		
		this.localRepository.save(local);
	}
	
	@After
	public final void tearDown() {
		this.localRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorDescricao() {
		Local local = this.localRepository.findByDescricao(LOCAL);
		
		assertEquals(LOCAL, local.getDescricao());
	}
}