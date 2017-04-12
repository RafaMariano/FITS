package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Produto;


public interface ProdutoService {
	public Produto buscar(long id);
	public List<Produto> buscarTodos();
	
}
