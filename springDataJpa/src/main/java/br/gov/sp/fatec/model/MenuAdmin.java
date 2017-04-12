package br.gov.sp.fatec.model;


import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.loja.Venda;
import br.gov.sp.fatec.service.FuncionarioServiceImpl;

@Service("menuAdmin")
public class MenuAdmin {
	private Scanner scan;
	private long id;
	
	@Autowired
	private FuncionarioServiceImpl funcionarioServiceImpl;
	
	public MenuAdmin() {
		this.scan = new Scanner(System.in);
	}
	
	public void setFuncionarioServiceImpl(FuncionarioServiceImpl funcionarioServiceImpl){
		this.funcionarioServiceImpl = funcionarioServiceImpl;
	}
	
	public boolean menuAdmin(Funcionario funcionarioAdmin) {

		System.out.println("Digite uma opção:");
		System.out.println("\n1) Cadastrar funcionário" + "\n2) Buscar funcionário" + "\n3) Excluir funcionário"
				+ "\n4) Vendas por Funcionario" + "\n5) Produto por Funcionario \n6) Sair");

		int escolha = scan.nextInt();

		switch (escolha) {
			case 1:
				Funcionario funcionario = createFunc();
				sendToDb(funcionario);
				break;
			case 2:
				this.id = searchFunc();
				getFunc(this.id);
				break;
			case 3:
				this.id = searchFunc();
				deleteFunc(this.id);
				break;
			case 4:
				this.id = searchFunc();
				getVendaByFunc(id);
				break;
			case 5:
				this.id = searchFunc();
				getProdutoByFunc(id);
				break;
			case 6:
				System.out.println("Bye" + funcionarioAdmin.getNome());
				return false;
		}
		return true;
	}
	
	
	public void getProdutoByFunc(Long id){
		List<Produto> listProduto = this.funcionarioServiceImpl.produtoByFuncionario(id);
		
		if (listProduto.isEmpty() ||listProduto == null){
			System.out.println("\n\nNada foi encontrado\n\n");
		}
		else{
			for (Produto produto: listProduto){
				System.out.println("\n\n-----------------------------------");
				System.out.println("--|ID DO PRODUTO|---|" + produto.getId()+"|---");
				System.out.println("-----------------------------------");
				System.out.println("---|NOME DO PRODUTO|---|" + produto.getNome()+"|---");
				System.out.println("-----------------------------------");
				System.out.println("--|PRECO DO PRODUTO|---|"+ produto.getPreco()+"|---");
				System.out.println("-----------------------------------\n\n");
			}
		}
	}
	
	
	public void getVendaByFunc(Long id){
		List<Venda> listVenda = this.funcionarioServiceImpl.vendasByFuncionario(id);

		if (listVenda.isEmpty() || listVenda == null){
			System.out.println("\n\nNada foi encontrado\n\n");
		}
		else{
			for (Venda venda: listVenda){
				System.out.println("\n\n-----------------------------------");
				System.out.println("---|NOME DO FUNCIONARIO|---|" + venda.getFuncionario().getNome()+"|---");
				System.out.println("-----------------------------------");
				System.out.println("--|NOME DO PRODUTO|---|"+ venda.getProduto().getNome()+"|---");
				System.out.println("-----------------------------------");
				System.out.println("--|DATA DA VENDA|---|" +venda.getData() +"|---");
				System.out.println("-----------------------------------\n\n");
			}
		}
	}
	
	@Transactional
	public void deleteFunc(Long id){
	if (this.funcionarioServiceImpl.deleteFuncionario(id))
		System.out.println("\n\nFuncionario deletado com sucesso\n\n");
	else
		System.out.println("Funcionario não existe");
	}
	
	public Long searchFunc(){
		System.out.println("De o ID do funcionario");
		long id = scan.nextLong();
		return id;
	}
	
	@Transactional
	public Funcionario createFunc() {
		System.out.println("Digite o nome do funcionario/Admin: ");
		String nome = scan.next();

		int choiceCargo = 0;
		String cargo;
		while (choiceCargo != 1 && choiceCargo != 2) {
			System.out.println("Qual cargo? 1- Funcionario 2- Admin ");
			choiceCargo = scan.nextInt();
		}
		
		if (choiceCargo == 1)
			cargo = "ADMIN";
		else
			cargo = "FUNCIONARIO";

		System.out.println("Coloqueo o RG do funcionario/Admin");
		String RG = scan.next();

		System.out.println("Coloque a senha");
		String senha = scan.next();

		Funcionario func = new Funcionario();
		func.setCargo(cargo);
		func.setNome(nome);
		func.setRG(RG);
		func.setSenha(senha);

		return func;
	}
	
	public void getFunc(Long id){
		Funcionario func = this.funcionarioServiceImpl.getFuncionario(id);
		if(func == null)
			System.out.println("\n\nFuncionario não encontrado\n\n");
		else{
			System.out.println("Dados do funcionario " + func.getNome());
			System.out.println("Cargo: "+ func.getCargo());
			System.out.println("Senha: "+ func.getSenha());
			System.out.println("RG: " + func.getRG());
		}
	}
	
	@Transactional
	public void sendToDb(Funcionario func){
		this.funcionarioServiceImpl.save(func);
	}
	
}
