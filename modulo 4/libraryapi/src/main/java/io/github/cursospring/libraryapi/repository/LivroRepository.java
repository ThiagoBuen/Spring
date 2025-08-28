package io.github.cursospring.libraryapi.repository;

import io.github.cursospring.libraryapi.model.Autor;
import io.github.cursospring.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */

public interface LivroRepository extends JpaRepository<Livro, UUID>
{

    //Query method
    List<Livro> findByAutor(Autor autor);
    List<Livro> findByTitulo(String titulo);

    // select * from where isbn = ?
    List<Livro> findByIsbn(String isbn);

    // select * from where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // select * from where titulo = ? or preco = ?
    List<Livro> findByTituloOrPreco(String titulo, BigDecimal preco);

    // select * from livro where data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);
    //Check for other spring jpa query methods to obtain the full list of query


    //JPQL - Java Persistence Query Language
    // select l.* from livro as l order by l.titulo
    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdernadoPorTituloAndPreco();

    /**
     * select a.*
     * from livro l
     * join autor a on a.id = l.id_autor
     */
    @Query(" select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    // select distinct l.* from livro l
    @Query(" select distinct l.titulo from Livro l ")
    List<String> listarNomesDiferentesLivros();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();
}
