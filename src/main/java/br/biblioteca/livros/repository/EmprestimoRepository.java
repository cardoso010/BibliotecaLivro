package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.beans.Emprestimo;

public interface EmprestimoRepository extends JpaRepository <Emprestimo, Long> {

}
