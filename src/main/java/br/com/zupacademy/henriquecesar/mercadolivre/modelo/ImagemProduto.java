package br.com.zupacademy.henriquecesar.mercadolivre.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


@Entity
public class ImagemProduto {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank @Column(nullable = false)
    private String link;
    
    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(@NotBlank String link, Produto produtos) {
        this.link = link;
        this.produto = produtos;
    }

    public String getLink() {
        return link;
    }

}
