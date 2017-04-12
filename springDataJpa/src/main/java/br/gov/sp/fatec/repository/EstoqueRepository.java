package br.gov.sp.fatec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.loja.Estoque;

public interface EstoqueRepository extends  CrudRepository<Estoque, Long> {
	
	@Query("SELECT u FROM Estoque u WHERE u.id = ?1")
	public Estoque findOne(Long id);
}
