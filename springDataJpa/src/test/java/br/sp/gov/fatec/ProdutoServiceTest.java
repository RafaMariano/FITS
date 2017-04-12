package br.sp.gov.fatec;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.loja.Estoque;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.service.EstoqueServiceImpl;
import br.gov.sp.fatec.service.ProdutoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@Rollback
@Transactional
public class ProdutoServiceTest {
	
	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	@Autowired
	private EstoqueServiceImpl estoqueServiceImpl;
	

	public void setEstoqueServiceImpl(EstoqueServiceImpl estoqueServiceImpl) {
		this.estoqueServiceImpl = estoqueServiceImpl;
	}
	
	public void setProdutoServiceImpl(ProdutoServiceImpl produtoServiceImpl){
		this.produtoServiceImpl = produtoServiceImpl;
	}
	
	@Test
	public void cadastrarProduto(){
		
		Produto produto = getProduto("Macarrã0", 4.5);
		Estoque estoque = getEstoque(1);
		
		Produto produto2 = getProduto("Feijão", 14.554444);
		Estoque estoque2 = getEstoque(1);
		
		Produto produto3 = getProduto("Tomate", 66.88885);
		Estoque estoque3 = getEstoque(1);

		assertTrue(this.produtoServiceImpl.cadastrar(produto, estoque, 1));
		assertTrue(this.produtoServiceImpl.cadastrar(produto2, estoque2, 1));
		assertTrue(this.produtoServiceImpl.cadastrar(produto3, estoque3, 1));
	}
	
	private Produto getProduto(String produtoNome, double produtoPreco){ 

		Produto produto = new Produto();
		
		produto.setNome(produtoNome);
		produto.setPreco(produtoPreco);
		return produto;
	}
	
	private Estoque getEstoque(long id){
		return this.estoqueServiceImpl.getEstoqueById(id);
	}
}
