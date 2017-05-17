package br.gov.sp.fatec.loja;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUNC_FUNCIONARIO")
public class Funcionario {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "FUNC_ID")
	private Long id;
    
    @Column(name = "FUNC_NOME", length = 20, nullable = false)
    private String nome;
    
    @Column(name = "FUNC_RG", length = 15, nullable = false)
    private String rg;
    
    @Column(name = "FUNC_CARGO", length = 25, nullable = false)
    private String cargo;
    
    @Column(name = "FUNC_SENHA", length = 20, nullable = false)
    private String senha;
        
	public Long getId() {
		return id;
	}
	
	public String getSenha(){
		return senha;
	}
	
	public void setSenha(String senha){
		this.senha = senha;
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
	
	public String getRG() {
		return rg;
	}

	public void setRG(String rg) {
		this.rg = rg;
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}