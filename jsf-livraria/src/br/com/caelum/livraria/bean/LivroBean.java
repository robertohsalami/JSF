package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravando Livro: " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			//Obtemos uma referência do contexto JSF no momento da chamada do método através de FacesContext.getCurrentInstance()
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}
		
		if(this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);			
		}else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		
		this.livro = new Livro();
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro escrito por: " + autor.getNome()); 
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		List<Livro> listaTodos = new DAO<Livro>(Livro.class).listaTodos();
		return listaTodos;
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN Deveria começar com 1"));
		}
	}
	
	public void remover(Livro livro) {
		new DAO<Livro>(Livro.class).remove(livro);		
	}	
		
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public String formAutor() {
		return "autor?faces-redirect=true";
	}

}
