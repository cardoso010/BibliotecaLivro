package br.biblioteca.livros.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Emprestimo {
	@Id
	@GeneratedValue
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data de emprestimo não pode ser vazio.")
	@Temporal(TemporalType.DATE)
	private Date dataEmprestimo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data de devolução não pode ser vazio.")
	@Temporal(TemporalType.DATE)
	private Date dataDevolucao;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Livro livro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
}
