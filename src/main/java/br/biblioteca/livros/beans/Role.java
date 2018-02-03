package br.biblioteca.livros.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;

	private String role;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Role(String role, Usuario usuario) {
		this.role = role;
		this.usuario = usuario;
	}
	
	public Role() {
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + "]";
	}

}
