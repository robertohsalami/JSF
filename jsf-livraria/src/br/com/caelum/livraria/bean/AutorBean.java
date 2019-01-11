package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transacional;
import br.com.caelum.livraria.util.RedirectView;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class AutorBean implements Serializable{

	private Autor autor = new Autor();
	private Integer autorId;
	@Inject
	private AutorDao dao;	

	public Autor getAutor() {
		return autor;
	}	
		
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	@Transacional
	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId() == null) {
			this.dao.adiciona(this.autor);			
		}else {
			this.dao.atualiza(autor);
		}
		
		
		//necessário para limpar os dados do componente.
		this.autor = new Autor();
		
		return new RedirectView("livro");
	}
	
	public List<Autor> getListaTodos(){
		return this.dao.listaTodos();
	}
	
	@Transacional
	public void removerAutor(Autor autor) {
		this.dao.remove(autor);
	}
	
	public void carregar(Autor autor) {
		this.autor = autor;
	}
	
	public void carregarAutorPeloId() {
		this.autor = this.dao.buscaPorId(autorId);		
	}
	
}
