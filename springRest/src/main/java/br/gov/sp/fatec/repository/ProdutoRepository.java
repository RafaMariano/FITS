package br.gov.sp.fatec.repository;


import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Produto;

public interface ProdutoRepository extends  CrudRepository<Produto, Long>{
	
		public Produto findById(Long id);
		public Iterable<Produto> findAll();
		public Iterable<Produto> findByNome(String nome);
		public Iterable<Produto> findByPreco(double preco);
		public Iterable<Produto> findByPrecoAndNome(double preco, String nome);
}
