package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.beans.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	public Usuario findByUsername(String username);
}
