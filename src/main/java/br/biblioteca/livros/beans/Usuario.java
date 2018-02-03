package br.biblioteca.livros.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "Usuário não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	private String username;
	
	@NotNull(message = "Email não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	@Email(message = "Email inválido.")
	private String email;
	
	@NotNull(message = "Senha não pode ser vazio.")
	@Size(min = 2, max = 100, message = "O tamanho deve ser entre 2 a 100 caracteres.")
	private String password;
	
	@OneToMany(mappedBy="usuario")
	private List<Review> reviews = new ArrayList<>();
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
