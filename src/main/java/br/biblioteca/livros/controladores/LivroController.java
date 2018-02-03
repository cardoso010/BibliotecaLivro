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

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.AutorRepository;
import br.biblioteca.livros.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@GetMapping("/list")
	public ModelAndView livros() {
		Iterable<Livro> livros = livroRepository.findAll();
		return new ModelAndView("livros/list", "livros", livros);
	}
	
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Livro livro) {
		ModelAndView modelAndView = new ModelAndView("livros/form");
		
		Iterable<Autor> autores = autorRepository.findAll();
		modelAndView.addObject("autores", autores);

		
		return modelAndView;

	}

	@PostMapping(params = "form")
	public ModelAndView create(@ModelAttribute("livro") @Valid Livro livro, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("livros/form");
		}
		
		livro = livroRepository.save(livro);
	   	 return new ModelAndView("redirect:/livros/list");
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		Livro livro = this.livroRepository.findOne(id);
		Iterable<Autor> autores = autorRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("livros/form");
		
		modelAndView.addObject("autores", autores);
		modelAndView.addObject("livro", livro);
		
		return modelAndView;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Livro livro = this.livroRepository.findOne(id);
		this.livroRepository.delete(livro);
		return new ModelAndView("redirect:/livros/list");
	}
}
