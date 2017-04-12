package br.gov.sp.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.loja.Estoque;
import br.gov.sp.fatec.repository.EstoqueRepository;

@Service("estoqueService")
public class EstoqueServiceImpl implements EstoqueService{
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	public void setEstoqueRepository (EstoqueRepository estoqueRepository){
		this.estoqueRepository = estoqueRepository;
	}
	
	public Estoque getEstoqueById(long id) {
		return this.estoqueRepository.findOne(id);
	}

}