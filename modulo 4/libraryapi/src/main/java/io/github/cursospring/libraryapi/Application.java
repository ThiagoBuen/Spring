package io.github.cursospring.libraryapi;

import io.github.cursospring.libraryapi.model.Autor;
import io.github.cursospring.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args)
    {
		SpringApplication.run(Application.class, args);
	}
}
