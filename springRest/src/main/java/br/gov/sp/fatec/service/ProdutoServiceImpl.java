package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

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
	public List<Produto> buscarTodos() {
		return Lists.newArrayList(this.produtoRepository.findAll());
	}

}
