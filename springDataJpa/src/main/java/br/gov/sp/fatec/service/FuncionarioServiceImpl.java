package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.loja.Venda;
import br.gov.sp.fatec.repository.FuncionarioRepository;

@Service("funcionarioService")
public class FuncionarioServiceImpl implements FuncionarioService{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public void setFuncionarioRepository(FuncionarioRepository funcionarioRepository){
		this.funcionarioRepository = funcionarioRepository;
	}


	public void save(Funcionario funcionario) {
		this.funcionarioRepository.save(funcionario);
		
	}

	public Funcionario getFuncionario(long id) {
		return this.funcionarioRepository.findOne(id);
		
	}

	public boolean deleteFuncionario(long id) {
		try{
		if (this.funcionarioRepository.exists(id)){
			this.funcionarioRepository.delete(id);
			return true;
		}
		return false;
		}
		catch(Error error){
			return false;
		}
	}
	

	public List<Venda> vendasByFuncionario(long id) {
		return this.funcionarioRepository.getVendasByFuncionario(id);
	}

	public List<Produto> produtoByFuncionario(long id) {
		return this.funcionarioRepository.getProdutoVendidosByFuncionario(id);
	}

}
