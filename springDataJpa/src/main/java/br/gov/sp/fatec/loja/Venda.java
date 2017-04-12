package br.gov.sp.fatec.loja;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "VEN_VENDA")
public class Venda {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "VEN_ID")
	private Long id;
	 
	@ManyToOne
	@JoinColumn(name="VEN_PRO_ID")
	private Produto produto;
	
    @ManyToOne
    @JoinColumn(name="VEN_FUNC_ID")
	private Funcionario funcionario;
	
	@Column(name = "VEN_DATA_HORA")
	@Type(type="timestamp")
	private Date data;

    
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}
	public void setFuncionario(Funcionario func) {
		this.funcionario = func;
	}
	
	public Object getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data; 
	}

}
