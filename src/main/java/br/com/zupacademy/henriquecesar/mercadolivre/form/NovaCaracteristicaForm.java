package br.com.zupacademy.henriquecesar.mercadolivre.form;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Caracteristica;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;

public class NovaCaracteristicaForm {
    
    @NotBlank
    private String nome;
    
    @NotBlank
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public Caracteristica toModel(Produto produto) {
        return new Caracteristica(nome, descricao, produto);
    }

}
