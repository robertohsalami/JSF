package br.com.caelum.livraria.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Venda {
	
	@Id @GeneratedValue
	private Integer id;
	
	//relacionamento com a tabela Livro. Uma venda est� associada com um livro.
	//Varias vendas s�o associadas com um livro
	@ManyToOne
	private Livro livro;
	private Integer quantidade;
	
	public Venda() {}

	public Venda(Livro livro, Integer quantidade) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
