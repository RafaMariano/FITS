package br.gov.sp.fatec.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.gov.sp.fatec.model.Produto;
import br.gov.sp.fatec.repository.ProdutoRepository;

@Service("produtoServiceImpl")
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public void setProdutoRepository(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Override
	public Produto buscar(long id) {
		return this.produtoRepository.findById(id);
	}

	@Override
	public Iterator<Produto> buscarTodos() {
		return this.produtoRepository.findAll().iterator();
	}

	@Override
	public Iterator<Produto> getProdutoNome(String nome) {
		return  this.produtoRepository.findByNome(nome).iterator();
	}

	@Override
	public Iterator<Produto> getProdutoPreco(double preco) {
		// TODO Auto-generated method stub
		return this.produtoRepository.findByPreco(preco).iterator();
	}

	@Override
	public Iterator<Produto> getProdutoNomePreco(String nome, double preco) {
		// TODO Auto-generated method stub
		return this.produtoRepository.findByPrecoAndNome(preco, nome).iterator();
	}

}
