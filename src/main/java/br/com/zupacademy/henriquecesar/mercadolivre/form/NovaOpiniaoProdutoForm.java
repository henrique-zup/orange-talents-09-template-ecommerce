package br.com.zupacademy.henriquecesar.mercadolivre.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;

public class NovaOpiniaoProdutoForm {
    
    @Range(min = 1, max = 5)
    private Integer nota;

    @NotBlank
    private String titulo;
    
    @NotBlank 
    @Size(max = 500)
    private String descricao;

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Opiniao toModel(Usuario usuario, Produto produto) {
        return new Opiniao(nota, titulo, descricao, usuario, produto);
    }

}
