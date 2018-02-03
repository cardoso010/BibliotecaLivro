package br.biblioteca.livros.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Autor {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "Nome não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	private String nome;
	
	@OneToMany(mappedBy="autor")
	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

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
}
