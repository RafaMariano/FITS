package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.loja.Estoque;
import br.gov.sp.fatec.loja.Produto;

public interface ProdutoService {
	public boolean cadastrar(Produto produto, Estoque estoque, long q);
	public Produto buscar(long id);
	public List<Produto> buscarTodos();

	
}
