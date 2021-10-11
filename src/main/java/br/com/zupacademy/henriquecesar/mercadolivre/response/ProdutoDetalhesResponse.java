package br.com.zupacademy.henriquecesar.mercadolivre.response;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;

public class ProdutoDetalhesResponse {

    private String nome;
    private BigDecimal preco;
    private Long quantidadeDisponivel;
    private Set<CaracteristicaResponse> caracteristicas;
    private Set<ImagemProdutoResponse> imagens;
    private Double mediaAvaliacoes;
    private Integer numeroAvaliacoes;
    private Set<OpiniaoResponse> opinioes;
    private Set<PerguntaResponse> perguntas;

    public ProdutoDetalhesResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
        this.caracteristicas = produto.getCaracteristicas()
                .stream().map(CaracteristicaResponse::new).collect(Collectors.toSet());
        this.imagens = produto.getImagens()
                .stream().map(ImagemProdutoResponse::new).collect(Collectors.toSet());
        this.mediaAvaliacoes = produto.getMediaAvaliacoes();
        this.numeroAvaliacoes = produto.getNumeroAvaliacoes();
        this.opinioes = produto.getOpinioes()
                .stream().map(OpiniaoResponse::new).collect(Collectors.toSet());
        this.perguntas = produto.getPerguntas()
                .stream().map(PerguntaResponse::new).collect(Collectors.toSet());
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }
    
    public Long getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public Set<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }
    
    public Set<ImagemProdutoResponse> getImagens() {
        return imagens;
    }

    public Double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public Integer getNumeroAvaliacoes() {
        return numeroAvaliacoes;
    }

    public Set<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaResponse> getPerguntas() {
        return perguntas;
    }

}