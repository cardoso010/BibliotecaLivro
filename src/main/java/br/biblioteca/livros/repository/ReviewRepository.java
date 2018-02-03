package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.beans.Review;

public interface ReviewRepository extends JpaRepository <Review, Long> {

}
