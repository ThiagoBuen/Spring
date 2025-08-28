package io.github.curso.spring.produtosapi.controller;

import io.github.curso.spring.produtosapi.model.Produto;
import io.github.curso.spring.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController     // Classe para receber requisições rest
@RequestMapping("produtos")
public class ProdutoController
{
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository)
    {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto)
    {
        System.out.println("Produto recebido: " + produto);
        var id = UUID.randomUUID().toString();
        produto.setId(id);
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("{id}")
    public Produto getProductById(@PathVariable String id)
    {
//        Optional<Produto> produto = produtoRepository.findById(id);
//        return produto.isPresent() ? produto.get() : null;

        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable String id)
    {
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Produto updateProductById(@PathVariable String id, @RequestBody Produto product)
    {
        product.setId(id);
        produtoRepository.save(product);

        return product;
    }

    @GetMapping
    public List<Produto> find(@RequestParam("nome") String nome)
    {
        return produtoRepository.findByNome(nome);
    }
}