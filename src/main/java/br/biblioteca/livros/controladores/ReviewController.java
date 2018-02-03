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

import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.Review;
import br.biblioteca.livros.repository.ReviewRepository;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@GetMapping("/list")
	public ModelAndView autores() {
		
		Iterable<Review> reviews = reviewRepository.findAll();
		return new ModelAndView("reviews/list", "reviews", reviews);
		
	}
	
	@GetMapping("/novo")
	public String createForm(@ModelAttribute Review review) {
		return "reviews/form";
	} 

	@PostMapping(params = "form")
	public ModelAndView create(@ModelAttribute("review") @Valid Review review, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("reviews/form");
		}
		
		review = this.reviewRepository.save(review);
	   	 return new ModelAndView("redirect:/reviews/list");
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		Review review = this.reviewRepository.findOne(id);
		return new ModelAndView("reviews/form", "review", review);
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Review review = this.reviewRepository.findOne(id);
		this.reviewRepository.delete(review);
		return new ModelAndView("redirect:/reviews/list");
	}	
	
}
