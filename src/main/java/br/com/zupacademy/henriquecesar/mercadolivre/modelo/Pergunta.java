package br.com.zupacademy.henriquecesar.mercadolivre.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String titulo;
    
    @NotNull
    private LocalDateTime data = LocalDateTime.now();
    
    @Valid
    @ManyToOne
    private Usuario usuario;
    
    @Valid
    @ManyToOne
    private Produto produto;
    
    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @Valid Usuario usuario, @Valid Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public @NotBlank String getTitulo() {
        return titulo;
    }

}
