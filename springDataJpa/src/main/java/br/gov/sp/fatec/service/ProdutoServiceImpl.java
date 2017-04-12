package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.loja.Estoque;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.loja.Quantidade;
import br.gov.sp.fatec.repository.ProdutoRepository;
import br.gov.sp.fatec.repository.QuantidadeRepository;

@Service("produtoServiceImpl")
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private QuantidadeRepository quantidadeRepository;

	public void setProdutoRepository(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public void setQuantidadeRepository(QuantidadeRepository quantidadeRepository) {
		this.quantidadeRepository = quantidadeRepository;
	}

	// public void cadastrar(Produto produto, Estoque estoque) {
	// Quantidade quantidade = existQuantidade(produto, estoque);
	//
	// quantidade.setQuantidade(quantidade.getQuantidade()+1);
	// this.produtoRepository.save(produto);
	// this.quantidadeRepository.save(quantidade);
	// }

	public boolean cadastrar(Produto produto, Estoque estoque, long q) {

		try {
			Quantidade quantidade = existQuantidade(produto, estoque);

			if (q <= 1)
				quantidade.setQuantidade(quantidade.getQuantidade() + 1);
			else
				quantidade.setQuantidade(quantidade.getQuantidade() + q);

			this.produtoRepository.save(produto);
			this.quantidadeRepository.save(quantidade);
			return true;
		} catch (Error error) {
			return false;
		}
	}

	private Quantidade existQuantidade(Produto produto, Estoque estoque) {

		Quantidade quant = this.quantidadeRepository.findOne(produto.getId(), estoque.getId());
		if (quant != null)
			return quant;
		quant = new Quantidade();
		quant.setEstoque(estoque);
		quant.setProduto(produto);
		return quant;
	}

	public Produto buscar(long id) {
		return this.produtoRepository.findById(id);
	}

	public List<Produto> buscarTodos() {
		return (List<Produto>) this.produtoRepository.findAll();
	}
}
