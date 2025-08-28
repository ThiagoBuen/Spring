package io.github.cursospring.libraryapi.repository;

import io.github.cursospring.libraryapi.model.Autor;
import io.github.cursospring.libraryapi.model.GeneroLivro;
import io.github.cursospring.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest
{
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest()
    {
        Autor author = new Autor();
        author.setNome("Jose");
        author.setNacionalidade("Brasileira");
        author.setDataNascimento(LocalDate.of(1950, 1, 31));

        var autorSalvo = autorRepository.save(author);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest()
    {
        var id = UUID.fromString("379706a3-9c82-4b9d-b470-cde6ac443d98");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent())
        {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Joao");
            autorRepository.save(autorEncontrado);

        }
        else
        {
            System.out.println("Autor nao encontrado");
        }

    }

    @Test
    public void listarTest()
    {
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest()
    {
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deleteTestById()
    {
        var id = UUID.fromString("379706a3-9c82-4b9d-b470-cde6ac443d98");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTestByObject()
    {
        Autor author = new Autor();
        author.setNome("Jose");
        author.setNacionalidade("Brasileira");
        author.setDataNascimento(LocalDate.of(1950, 1, 31));

        //or author =  autorRepository.findById(id).get();
        autorRepository.delete(author);
    }

    @Test
    public void salvarAutorComLivros()
    {
        Autor author = new Autor();
        author.setNome("Jose");
        author.setNacionalidade("Brasileira");
        author.setDataNascimento(LocalDate.of(1950, 1, 31));

        Livro livro = new Livro();
        livro.setIsbn("9852-5885");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("LOTR");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro.setAutor(author);

        author.setLivros(new ArrayList<>());
        author.getLivros().add(livro);

        autorRepository.save(author);

        livroRepository.saveAll(autor.getLivros());     //usando cascade all n precisa usar o livroRepository para os livros junto
    }

    @Test
    public void listarLivrosAutor()
    {
        UUID autorId = UUID.fromString("4fece7db-caa9-4878-9333-321d166f86e9");
        var autor = autorRepository.findById(autorId).get();
        List<Livro> livroLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);
        autor.getLivros().forEach(System.out::println);
    }
}
