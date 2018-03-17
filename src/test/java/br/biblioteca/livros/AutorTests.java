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

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.AutorRepository;
import br.biblioteca.livros.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutorTests {
	
	@Autowired
	AutorRepository autorRepository;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void buscaAutoresCadastrados() {

		Page<Autor> autores = this.autorRepository.findAll(new PageRequest(0, 10));
		assertThat(autores.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaAutorManoel() {
		
		Autor autorNaoEncontrado = this.autorRepository.findByNome("09090909");
		assertThat(autorNaoEncontrado).isNull();
		
		Autor autorEncontrado = this.autorRepository.findByNome("Manoel");
		assertThat(autorEncontrado).isNotNull();
		assertThat(autorEncontrado.getNome()).isEqualTo("Manoel");
		
	}
	
	@Test
	public void testAutorForm() throws Exception {
		
		this.mvc.perform(post("/autor/?form")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"nome\": \"teste\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testAlteraAutorManoel() {
		Autor autorManoel = this.autorRepository.findByNome("Manoel");
		autorManoel.setNome("Manuel");
		
		this.autorRepository.save(autorManoel);
		
		Autor usuarioManuelAlterado = this.autorRepository.findByNome("Manoel");
		assertThat(usuarioManuelAlterado).isNull();
		
		
	}
	
	@Test
	public void testCriaAutorMarlei1() {
		Autor marlei = new Autor();
		marlei.setNome("marlei");
		
		this.autorRepository.save(marlei);
		
		Autor usuarioMarlei = this.autorRepository.findByNome("marlei");
		assertThat(usuarioMarlei.getNome()).isEqualTo("marlei");
		
	}
	
	@Test
	public void testExcluiAutorMarlei2() {
		Autor autorMarlei = this.autorRepository.findByNome("marlei");
		this.autorRepository.delete(autorMarlei);
		
		Autor autorMarleiExcluido = this.autorRepository.findByNome("marlei");
		
		
		assertThat(autorMarleiExcluido).isNull();
	}

}
