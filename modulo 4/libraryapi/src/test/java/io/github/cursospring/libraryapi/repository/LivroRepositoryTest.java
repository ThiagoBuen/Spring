package io.github.cursospring.libraryapi.repository;

import io.github.cursospring.libraryapi.model.Autor;
import io.github.cursospring.libraryapi.model.GeneroLivro;
import io.github.cursospring.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest()
    {
        Livro livro = new Livro();
        livro.setIsbn("9852-5885");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("LOTR");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("4fece7db-caa9-4878-9333-321d166f86e9"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest()
    {
        Livro livro = new Livro();
        livro.setIsbn("9852-5885");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("LOTR");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor author = new Autor();
        author.setNome("Jose");
        author.setNacionalidade("Brasileira");
        author.setDataNascimento(LocalDate.of(1950, 1, 31));

        livro.setAutor(author);

        repository.save(livro);
    }

    @Test
    void AtualizarAutorLivro()
    {
        UUID livroId = UUID.fromString("4fece7db-caa9-4878-9333-321d166f86e9");
        var livroParaAtualizar = repository.findById().orElse(null);
        UUID autorId = UUID.fromString("4fece7db-caa9-4878-9333-321d166f86e9");
        Autor autor = autorRepository
                .findById(autorId)
                .orElse(null);
        livroParaAtualizar.setAutor(autor);
        repository.save(livroParaAtualizar);
    }

    @Test
    void deletarById()
    {
        UUID livroId = UUID.fromString("4fece7db-caa9-4878-9333-321d166f86e9");
        repository.deleteById(livroId);
    }

    @Test
    @Transactional          //para carregar mais dados... Qnd lazy e quiser pegar os dados do autor
    void buscarLivroTest()
    {
        UUID livroId = UUID.fromString("4fece7db-caa9-4878-9333-321d166f86e9");
        repository.findById(livroId).orElse(null);
        System.out.println(livroId.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisarPorTituloTest()
    {
        List<Livro> lista = repository.findByTitulo("");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorISBNTest()
    {
        List<Livro> lista = repository.findByIsbn("");
        lista.forEach(System.out::println);
    }

    @Test
    void listLivroComQueryJPQL()
    {
        var resultado = repository.listarTodosOrdernadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listAutoresDosLivros()
    {
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarNomesDiferentes()
    {
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeAutoresBrasileiros()
    {
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

}