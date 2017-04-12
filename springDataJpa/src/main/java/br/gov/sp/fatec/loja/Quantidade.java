package br.gov.sp.fatec.loja;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QUANT_QUANTIDADE")
public class Quantidade {

	public Quantidade(){
		if (getQuantidade() == null)
			setQuantidade(Integer.toUnsignedLong(0));
	}
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "QUANT_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="QUANT_PRO_ID")
	private Produto produto;
	
    @ManyToOne
    @JoinColumn(name="QUANT_EST_ID")
	private Estoque estoque;
	
    @Column(name = "QUANT_QTD", nullable = false)
	private Long quantidade;
	
	public Long getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Estoque getEstoque() {
		return this.estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

}
