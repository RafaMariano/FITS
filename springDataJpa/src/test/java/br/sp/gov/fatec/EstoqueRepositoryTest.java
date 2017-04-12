package br.sp.gov.fatec;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.gov.sp.fatec.loja.Estoque;
import br.gov.sp.fatec.repository.EstoqueRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@Rollback
@Transactional
public class EstoqueRepositoryTest {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	private static long id = 1;
	
	public void setEstoqueRepository(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}

	@Test
	public void testReturnNotNull(){
		Assert.notNull(this.estoqueRepository.findOne(id));
	}
	
	@Test
	public void testNomeId() {
		
		Estoque estoque = new Estoque();
		estoque.setId(id);
		estoque.setNome("ARMAZEM");	
		Estoque estoqueDb = this.estoqueRepository.findOne(id);
		
		assertTrue(estoque.getId() == estoqueDb.getId());
		assertTrue(estoque.getNome().equals(estoqueDb.getNome()));
	}

}
