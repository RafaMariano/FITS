package br.gov.sp.fatec.loja;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRO_PRODUTO")
public class Produto {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
	private Long id;
    
    @Column(name = "PRO_NOME", unique=true, length = 20, nullable = false)
    private String nome;
    
    @Column(name = "PRO_PRECO") 
    private double preco;

 
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
    	this.id = id;
	}
    

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
