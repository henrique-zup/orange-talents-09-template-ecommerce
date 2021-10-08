package br.com.zupacademy.henriquecesar.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henriquecesar.mercadolivre.form.NovoProdutoForm;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @PostMapping
    public void cadastrarProduto(@RequestBody @Valid NovoProdutoForm form) {
        Produto produto = form.toModel(categoriaRepository);
        produtoRepository.save(produto);
        
    }

}
