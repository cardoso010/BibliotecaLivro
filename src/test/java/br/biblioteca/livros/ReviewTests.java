package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.biblioteca.livros.beans.Review;
import br.biblioteca.livros.repository.ReviewRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReviewTests {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void buscaReviewsCadastrados() {

		Page<Review> reviews = this.reviewRepository.findAll(new PageRequest(0, 10));
		assertThat(reviews.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaReviewsComentarioBom() {
		
		Review naoEncontrado = this.reviewRepository.findByComentario("Muito ruim");
		assertThat(naoEncontrado).isNull();
		
		Review encontrado = this.reviewRepository.findByComentario("Bom");
		assertThat(encontrado).isNotNull();
		assertThat(encontrado.getComentario()).isEqualTo("Bom");
		
	}
	
	@Test
	public void buscaReviewsComAvaliacao4() {
		List<Review> igual4 = this.reviewRepository.findByAvaliacao(4);
		assertThat(igual4.size()).isGreaterThan(1);
	}
	
	

}
