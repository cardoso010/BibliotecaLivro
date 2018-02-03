package br.biblioteca.livros.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Cliente;
import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.ClienteRepository;
import br.biblioteca.livros.repository.EmprestimoRepository;
import br.biblioteca.livros.repository.LivroRepository;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/list")
	public ModelAndView emprestimos() {
		
		Iterable<Emprestimo> emprestimos = emprestimoRepository.findAll();
		return new ModelAndView("emprestimos/list", "emprestimos", emprestimos);
		
	}
	
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Emprestimo emprestimo) {
		ModelAndView modelAndView = new ModelAndView("emprestimos/form");
		
		Iterable<Cliente> clientes = clienteRepository.findAll();
		Iterable<Livro> livros = livroRepository.findAll();
		
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("livros", livros);

		
		return modelAndView;
	}
	
	@PostMapping(params = "form")
	public ModelAndView create(@ModelAttribute("emprestimo") @Valid Emprestimo emprestimo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("emprestimos/form");
			
			Iterable<Cliente> clientes = clienteRepository.findAll();
			Iterable<Livro> livros = livroRepository.findAll();
			
			modelAndView.addObject("clientes", clientes);
			modelAndView.addObject("livros", livros);

			
			return modelAndView;
		}
		
		emprestimo = emprestimoRepository.save(emprestimo);
	   	 return new ModelAndView("redirect:/emprestimos/list");
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("emprestimos/form");
		
		Emprestimo emprestimo = this.emprestimoRepository.findOne(id);
		
		Iterable<Cliente> clientes = clienteRepository.findAll();
		Iterable<Livro> livros = livroRepository.findAll();
		
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("livros", livros);
		modelAndView.addObject("emprestimo", emprestimo);
		
		return modelAndView;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Emprestimo emprestimo = this.emprestimoRepository.findOne(id);
		this.emprestimoRepository.delete(emprestimo);
		return new ModelAndView("redirect:/emprestimos/list");
	}	
}
