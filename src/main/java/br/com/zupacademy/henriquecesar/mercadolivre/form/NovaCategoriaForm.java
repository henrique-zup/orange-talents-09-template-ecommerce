package br.com.zupacademy.henriquecesar.mercadolivre.form;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.henriquecesar.mercadolivre.form.validator.ExisteId;
import br.com.zupacademy.henriquecesar.mercadolivre.form.validator.ValorUnico;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Categoria;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.CategoriaRepository;

public class NovaCategoriaForm {

    @NotBlank @ValorUnico(entityClass = Categoria.class, field = "nome")
    private String nome;

    @ExisteId(entityClass = Categoria.class, required = false)
    private Long categoriaMaeId;

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }

    public Categoria toModel(
            @ExisteId(entityClass = Categoria.class, required = false) CategoriaRepository categoriaRepository) {
        
        Categoria categoriaMae = Objects.isNull(categoriaMaeId) ? null
                : categoriaRepository.findById(categoriaMaeId).get();
        
        return new Categoria(nome, categoriaMae);
    }

}
