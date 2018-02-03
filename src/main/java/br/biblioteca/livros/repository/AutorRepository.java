package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.beans.Autor;

public interface AutorRepository extends JpaRepository <Autor, Long> {

}
