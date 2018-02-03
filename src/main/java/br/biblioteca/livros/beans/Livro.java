package br.biblioteca.livros.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Livro {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "Nome não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	private String nome;
	
	@Lob
	private String foto;
	
	@NotNull(message = "Quantidade não pode ser vazio.")
	@Min(value = 10, message = "valor mínimo é 10 páginas")
	private Integer quantidade;
	
	@NotNull(message = "Quantidade de páginas não pode ser vazio.")
	private Integer quantidadePaginas;
	
	@NotNull(message = "ISBN não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	private String isbn;
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;
	
	@OneToMany(mappedBy="livro")
	private List<Review> reviews = new ArrayList<>();
	
	@OneToMany(mappedBy="livro")
	private List<Emprestimo> emprestimos = new ArrayList<>();

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(Integer quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}
}
