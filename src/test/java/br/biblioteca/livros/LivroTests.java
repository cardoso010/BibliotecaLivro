package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LivroTests {
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void buscaCadastrados() {

		Page<Livro> item = this.livroRepository.findAll(new PageRequest(0, 10));
		assertThat(item.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaLivroBom() {
		
		Livro naoEncontrado = this.livroRepository.findByNome("1231231");
		assertThat(naoEncontrado).isNull();
		
		Livro encontrado = this.livroRepository.findByNome("Um bom livro");
		assertThat(encontrado).isNotNull();
		assertThat(encontrado.getNome()).isEqualTo("Um bom livro");
		
	}
	
	@Test
	public void testLivroForm() throws Exception {
		
		this.mvc.perform(post("/livro/?form")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"nome\": \"teste\", \"quantidade\": \"12\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testAltera() {
		Livro alterar = this.livroRepository.findByNome("A vida de programador");
		alterar.setNome("A vida do programador Joao");
		
		this.livroRepository.save(alterar);
		
		Livro antigo = this.livroRepository.findByNome("A vida de programador");
		assertThat(antigo).isNull();
		
		
	}
	
	@Test
	public void testCriaLivroEspetacular() {
		Livro espetacular = new Livro();
		espetacular.setNome("Espetacular");
		espetacular.setIsbn("3123231");
		espetacular.setQuantidadePaginas(2000);
		espetacular.setQuantidade(123);
		
		this.livroRepository.save(espetacular);
		
		Livro espetacularCriado = this.livroRepository.findByNome("Espetacular");
		assertThat(espetacularCriado.getNome()).isEqualTo("Espetacular");
		
	}
	
	@Test
	public void testExcluiMarlei2() {
		Livro espetacular = this.livroRepository.findByNome("Espetacular");
		this.livroRepository.delete(espetacular);
		
		Livro espetacularExcluido = this.livroRepository.findByNome("Espetacular");
		
		
		assertThat(espetacularExcluido).isNull();
	}

}
