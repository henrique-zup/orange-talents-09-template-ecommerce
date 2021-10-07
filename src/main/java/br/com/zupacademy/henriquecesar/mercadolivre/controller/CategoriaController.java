package br.com.zupacademy.henriquecesar.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henriquecesar.mercadolivre.form.NovaCategoriaForm;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Categoria;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @PostMapping
    public void cadastraCategoria(@Valid @RequestBody NovaCategoriaForm form) {
        Categoria categoria = form.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
    }

}
