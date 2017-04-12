package br.gov.sp.fatec.model;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.loja.Estoque;
import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.loja.Produto;
import br.gov.sp.fatec.service.EstoqueServiceImpl;
import br.gov.sp.fatec.service.ProdutoServiceImpl;
import br.gov.sp.fatec.service.VendaImpl;

@Service("menuFunc")
public class MenuFunc {
	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	@Autowired
	private VendaImpl vendaImpl;
	@Autowired
	private EstoqueServiceImpl estoqueServiceImpl;
	private Scanner scan;
	private long id;
	private Produto produto;
	private Estoque estoque;
	
	public MenuFunc(){
		this.scan = new Scanner(System.in);
	}
	
	public void setVendaImpl(VendaImpl vendaImpl){
		this.vendaImpl = vendaImpl;
	}
	
	public void setEstoqueServiceImpl(EstoqueServiceImpl estoqueServiceImpl) {
		this.estoqueServiceImpl = estoqueServiceImpl;
	}
	
	public void setProdutoServiceImpl(ProdutoServiceImpl produtoServiceImpl){
		this.produtoServiceImpl = produtoServiceImpl;
	}
	
	public boolean menuFunc(Funcionario funcionario){
			
			System.out.println("Digite uma opção:");
			System.out.println("\n1) Cadastrar produto" +
					"\n2) Buscar produto" +
					"\n3) Realizar venda"+
					"\n4) Sair");
			int escolha = scan.nextInt();
			
			switch(escolha){
				case 1:
					this.produto = getProduto();
					this.estoque = getEstoque();
					System.out.println("Quantidade de produtos para cadastrar: OBS - Se colocar 0 ou menos, será cadastrado um produto");
					long q = scan.nextLong();
					
					if(sendToBD(produto, estoque, q))
						System.out.println("Produto cadastrado com sucesso.");
					else
						System.out.println("Falha no cadastro.");
					
					break;
				case 2:
					this.id = searchProduto();
					this.produto = searchDB(this.id);
					format(this.produto);
					break;
				case 3:
					this.id = searchProduto();
					this.produto = searchDB(this.id);
					sendVendaToDB(funcionario, this.produto);
					break;
				case 4:
					System.out.println("Bye " + funcionario.getNome());
					return false;// agora vou apertar o 4 o certoé aparecer false la embaixo ne?sss
			} 
			return true;
	}
		
		@Transactional
		private void sendVendaToDB(Funcionario func, Produto produto){
			this.vendaImpl.cadastrarVenda(func, produto);
		}
		
		private void format(Produto produto){
			System.out.println("---------------------------------");
			System.out.println("|Id:  "  + produto.getId() + "|");
			System.out.println("|Nome do Produto:  " + produto.getNome() + "|");
			System.out.println("|Preco do produto:  " + produto.getPreco() + "|");
			System.out.println("----------------------------------\n\n");
		}

		private long searchProduto(){
			System.out.println("Id do produto: ");
			return scan.nextLong();
		}
		
		private Produto searchDB(long id){
			return this.produtoServiceImpl.buscar(id);
		}
		
		private Estoque getEstoque(){
			System.out.println("Id do estoque");
			long id = this.scan.nextLong();
			return this.estoqueServiceImpl.getEstoqueById(id);
		}
		
		private Produto getProduto(){ 

			Produto produto = new Produto();
			
			System.out.println("Nome do produto e preco");
			String produtoNome = scan.next();
			double produtoPreco = scan.nextDouble();
			produto.setNome(produtoNome);
			produto.setPreco(produtoPreco);
			return produto;
			
		}
		
//		@Transactional
//		private void sendToBD(Produto produto, Estoque estoque){
//			this.produtoServiceImpl.cadastrar(produto, estoque);
//		}
//		
		@Transactional
		private boolean sendToBD(Produto produto, Estoque estoque, long q){
			return this.produtoServiceImpl.cadastrar(produto, estoque, q);
		}
	
	}