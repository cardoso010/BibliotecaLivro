package br.biblioteca.livros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.beans.Review;
import br.biblioteca.livros.beans.Usuario;

public interface ReviewRepository extends JpaRepository <Review, Long> {
	public Review findByComentario(String comentario);
	public List<Review> findByAvaliacao(Integer avaliacao);

}
