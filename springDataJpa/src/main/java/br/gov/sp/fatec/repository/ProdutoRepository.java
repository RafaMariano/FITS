package br.gov.sp.fatec.repository;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.loja.Produto;

public interface ProdutoRepository extends  CrudRepository<Produto, Long>{
		public Produto findById(Long id);
}
