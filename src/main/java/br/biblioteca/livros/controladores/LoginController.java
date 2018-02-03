package br.biblioteca.livros.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.services.SecurityService;
import br.biblioteca.livros.validator.LoginValidator;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private LoginValidator loginValidator;

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login/form", "loginForm", new Usuario());
	}

	@PostMapping("/autentication")
	public ModelAndView autentication(@ModelAttribute("loginForm") Usuario userForm, BindingResult bindingResult,
			Model model) {
		loginValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return new ModelAndView("login/form");
		}

		securityService.login(userForm.getUsername(), userForm.getPassword());
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
		}

		return "redirect:/login/login";
	}

}
