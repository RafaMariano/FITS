package br.gov.sp.fatec.service;

import br.gov.sp.fatec.loja.Funcionario;

public interface Login {
	public boolean login(long id, String senha);
	public Funcionario getFunc(long id);
}
