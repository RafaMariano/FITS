package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.loja.Venda;

public interface FuncionarioService {
	
	public void save(Funcionario funcionario);
	public Funcionario getFuncionario(long id);
	public boolean deleteFuncionario(long id);
	public List<Venda> vendasByFuncionario(long id);
	public List<Produto> produtoByFuncionario (long id);

}
