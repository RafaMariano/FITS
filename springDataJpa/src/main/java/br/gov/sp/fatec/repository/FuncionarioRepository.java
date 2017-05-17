package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.loja.Venda;

public interface FuncionarioRepository extends  CrudRepository<Funcionario, Long>{

	@Query("SELECT CASE WHEN id = ?1 AND senha = ?2 then true"
			+ " ELSE false END FROM Funcionario"
			+ " WHERE id = ?1")
	Boolean exist(Long id, String senha);
	
	@Query("SELECT p FROM Produto p, Venda v, Funcionario f"
			+ " WHERE p.id = v.id AND f.id = v.id AND f.id = ?1")
	public List<Produto> getProdutoVendidosByFuncionario(Long func_id);
	
	@Query("SELECT v FROM Funcionario f, Produto p, Venda v"
			+ " WHERE p.id = v.id AND f.id = v.id AND f.id = ?1")
	public List<Venda> getVendasByFuncionario(Long id);
	
}