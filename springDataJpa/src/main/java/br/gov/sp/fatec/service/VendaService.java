package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.loja.Venda;

public interface VendaService {
	public void cadastrarVenda(Funcionario func, Produto produto);
	public List<Venda> consultarTodaVenda();
	public Venda consultarVenda(long id);
}
