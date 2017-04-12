package br.gov.sp.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.repository.FuncionarioRepository;

@Service("loginImpl")
public class LoginImpl implements Login{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	public void setFuncionarioRepository(FuncionarioRepository funcRepo){
		this.funcionarioRepository = funcRepo;
	}
	

	public boolean login(long id, String senha) {
		
		Boolean bool = this.funcionarioRepository.exist(id, senha);
		if (bool == null){
			return false;
		}
		return this.funcionarioRepository.exist(id, senha);
			
	}
	

	public Funcionario getFunc(long id){
		return this.funcionarioRepository.findOne(id);
	}
}
