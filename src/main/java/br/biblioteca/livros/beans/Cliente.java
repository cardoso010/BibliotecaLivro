package br.biblioteca.livros.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue
	@Column(name="id_cliente")
	private Long id;
	
	@NotNull(message = "Nome não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	private String nome;
	
	@NotNull(message = "Endereço não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	private String endereco;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data de nascimento não pode ser vazio.")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Lob
	private String observacao;
	
	@OneToMany(mappedBy="cliente")
	private List<Emprestimo> emprestimos = new ArrayList<>();
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
	public String getNome() {
		return nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
