package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);			
		}else {
			new DAO<Autor>(Autor.class).atualiza(autor);
		}
		
		
		//necessário para limpar os dados do componente.
		this.autor = new Autor();
		
		return new RedirectView("livro");
	}
	
	public List<Autor> getListaTodos(){
		return new DAO(Autor.class).listaTodos();
	}
	
	public void removerAutor(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
	}
	
	public void carregar(Autor autor) {
		this.autor = autor;
	}
}
