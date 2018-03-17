package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.beans.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
	public Cliente findByNome(String nome);
}
