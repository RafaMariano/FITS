package br.gov.sp.fatec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.loja.Quantidade;

public interface QuantidadeRepository extends  CrudRepository<Quantidade, Long>{
	
	@Query("SELECT u FROM Quantidade u WHERE u.produto.id = ?1 AND u.estoque.id = ?2")
	public Quantidade findOne(Long produtoId, Long estoqueId);
	
}
