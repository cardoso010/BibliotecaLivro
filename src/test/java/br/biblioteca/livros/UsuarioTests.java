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

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTests {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void buscaUsuariosCadastrados() {

		Page<Usuario> usuarios = this.usuarioRepository.findAll(new PageRequest(0, 10));
		assertThat(usuarios.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaUsuariosAdmin() {
		
		Usuario usuarioNaoEncontrado = this.usuarioRepository.findByUsername("José");
		assertThat(usuarioNaoEncontrado).isNull();
		
		Usuario usuarioEncontrado = this.usuarioRepository.findByUsername("admin");
		assertThat(usuarioEncontrado).isNotNull();
		assertThat(usuarioEncontrado.getUsername()).isEqualTo("admin");
		assertThat(usuarioEncontrado.getEmail()).isEqualTo("admin@admin.com.br");
		
	}
	
	@Test
	public void testUsuarioForm() throws Exception {
		
		this.mvc.perform(post("/usuarios/?form")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"username\": \"gabiel\", \"email\": \"gabrie123l@gabriel.com.br\", \"password\": \"123\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testAlteraUsuarioManuel() {
		Usuario usuarioManoel = this.usuarioRepository.findByUsername("manuel");
		usuarioManoel.setEmail("manuel@gmail.com");
		
		this.usuarioRepository.save(usuarioManoel);
		
		Usuario usuarioManoelAlterado = this.usuarioRepository.findByUsername("manuel");
		assertThat(usuarioManoelAlterado.getEmail()).isEqualTo("manuel@gmail.com");
		
		
	}
	
	@Test
	public void testCriaUsuarioJackon1() {
		Usuario jackon = new Usuario();
		jackon.setUsername("jackon");
		jackon.setEmail("jackon@gmail.com");
		jackon.setPassword("jackon@123");
		
		this.usuarioRepository.save(jackon);
		
		Usuario usuarioManoel = this.usuarioRepository.findByUsername("jackon");
		assertThat(usuarioManoel.getEmail()).isEqualTo("jackon@gmail.com");
		
	}
	
	@Test
	public void testExcluiUsuarioJackon2() {
		Usuario usuarioManoel = this.usuarioRepository.findByUsername("jackon");
		this.usuarioRepository.delete(usuarioManoel);
		
		Usuario usuarioManoelExcluido = this.usuarioRepository.findByUsername("jackon");
		
		
		assertThat(usuarioManoelExcluido).isNull();
	}
	

}
