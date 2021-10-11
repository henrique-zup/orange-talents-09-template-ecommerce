package br.com.zupacademy.henriquecesar.mercadolivre.response;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Caracteristica;

public class CaracteristicaResponse {

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

    public CaracteristicaResponse(@Valid Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

}
