package br.biblioteca.livros.services;

import java.util.List;

import br.biblioteca.livros.beans.Usuario;

public interface UsuarioService {
	
	void save(Usuario user);

	Usuario findByUsername(String username);

	List<Usuario> findAll();
	
}