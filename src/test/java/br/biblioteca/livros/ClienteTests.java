package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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

import br.biblioteca.livros.beans.Cliente;
import br.biblioteca.livros.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTests {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void buscaClientesCadastrados() {

		Page<Cliente> clientes = this.clienteRepository.findAll(new PageRequest(0, 10));
		assertThat(clientes.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaClienteMarcos() {
		
		Cliente naoEncontrado = this.clienteRepository.findByNome("1231231");
		assertThat(naoEncontrado).isNull();
		
		Cliente encontrado = this.clienteRepository.findByNome("Marcos");
		assertThat(encontrado).isNotNull();
		assertThat(encontrado.getNome()).isEqualTo("Marcos");
		
	}
	
	@Test
	public void testClienteForm() throws Exception {
		
		this.mvc.perform(post("/cliente/?form")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"nome\": \"teste\", \"endereco\": \"test31231e\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testAlteraClienteMauricio() {
		Cliente autorMauricio = this.clienteRepository.findByNome("Mauricio");
		autorMauricio.setNome("Mauricioooo");
		
		this.clienteRepository.save(autorMauricio);
		
		Cliente antigo = this.clienteRepository.findByNome("Mauricio");
		assertThat(antigo).isNull();
		
		
	}
	
	@Test
	public void testCriaClienteMarlei1() {
		Cliente marlei = new Cliente();
		marlei.setNome("marlei");
		marlei.setEndereco("Rua do marlei");
		marlei.setDataNascimento(new Date());
		
		this.clienteRepository.save(marlei);
		
		Cliente marleiCriado = this.clienteRepository.findByNome("marlei");
		assertThat(marleiCriado.getNome()).isEqualTo("marlei");
		
	}
	
	@Test
	public void testExcluiMarlei2() {
		Cliente marlei = this.clienteRepository.findByNome("marlei");
		this.clienteRepository.delete(marlei);
		
		Cliente marleiExcluido = this.clienteRepository.findByNome("marlei");
		
		
		assertThat(marleiExcluido).isNull();
	}

}
