package br.gov.sp.fatec.service;


import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.loja.Venda;
import br.gov.sp.fatec.repository.VendaRepository;

@Service("vendaImpl")
public class VendaImpl implements VendaService{

	
	private VendaRepository vendaRepository;
	
	public void setVendaRepository(VendaRepository repo){
		this.vendaRepository = repo;
	}
	
	public void cadastrarVenda(Funcionario func, Produto produto) {
		Venda venda = new Venda();
		venda.setFuncionario(func);
		venda.setProduto(produto);
		venda.setData(Calendar.getInstance().getTime());
		this.vendaRepository.save(venda);
		
	}

	public List<Venda> consultarTodaVenda() {
		return (List<Venda>) this.vendaRepository.findAll();
	}

	public Venda consultarVenda(long id) {
		return this.vendaRepository.findOne(id);
	}

}
