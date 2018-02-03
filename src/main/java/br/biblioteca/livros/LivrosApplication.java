package br.biblioteca.livros;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class LivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrosApplication.class, args);
	}
	
	@Bean
	public SessionLocaleResolver localeResolver() {
	    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	    sessionLocaleResolver.setDefaultLocale(new Locale ("pt", "BR"));
	    return sessionLocaleResolver;
	}
}
