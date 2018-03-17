package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.repository.EmprestimoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmprestimoTests {
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void buscaEmprestimosCadastrados() {

		Page<Emprestimo> emprestimos = this.emprestimoRepository.findAll(new PageRequest(0, 10));
		assertThat(emprestimos.getTotalElements()).isGreaterThan(1L);

	}

}
