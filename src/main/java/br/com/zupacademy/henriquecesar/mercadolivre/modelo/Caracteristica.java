package br.com.zupacademy.henriquecesar.mercadolivre.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank @Column(nullable = false)
    private String nome;
    
    @NotBlank @Column(nullable = false)
    private String descricao;
    
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Caracteristica() {
    }

    public Caracteristica(@NotBlank String nome, @NotBlank String descricao, @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto getProduto() {
        return produto;
    }
}
