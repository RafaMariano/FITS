package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import br.gov.sp.fatec.model.Funcionario;
import br.gov.sp.fatec.repository.FuncionarioRepository;

@Service("funcionarioService")
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public void setFuncionarioRepository(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public Funcionario save(Funcionario funcionario) {
		return this.funcionarioRepository.save(funcionario);

	}

	public Funcionario getFuncionario(long id) {
		return this.funcionarioRepository.findOne(id);

	}

	@Override
	public List<Funcionario> getAll() {
		return Lists.newArrayList(this.funcionarioRepository.findAll());
	}

}
