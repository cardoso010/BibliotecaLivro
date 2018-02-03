package br.biblioteca.livros.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "Avaliação não pode ser vazio.")
	@Min(value = 1, message = "Nota mínima é 1.")
	@Max(value = 5, message = "Nota mínima é 5.")
	private Integer avaliacao;
	
	@NotNull(message = "Comentário não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	private String comentario;
	
	@ManyToOne
	private Livro livro;
	
	@ManyToOne
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
