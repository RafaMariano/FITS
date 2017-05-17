package br.gov.sp.fatec.service;

import java.util.Iterator;
import br.gov.sp.fatec.model.Produto;


public interface ProdutoService {
	public Produto buscar(long id);
	public Iterator<Produto> buscarTodos();
	public Iterator<Produto> getProdutoNome(String nome);
	public Iterator<Produto> getProdutoPreco(double preco);
	public Iterator<Produto> getProdutoNomePreco(String nome, double preco);
	
	
}
