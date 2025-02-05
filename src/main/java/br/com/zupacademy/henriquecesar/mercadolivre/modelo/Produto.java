package br.com.zupacademy.henriquecesar.mercadolivre.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.henriquecesar.mercadolivre.repository.ProdutoRepository;

@Entity
public class Produto {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank @Column(nullable = false)
    private String nome;
    
    @NotNull @PositiveOrZero @Column(nullable = false)
    private Long quantidadeDisponivel;
    
    @NotNull @Column(nullable = false)
    private BigDecimal valor;
    
    @Size(min = 3) @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Caracteristica> caracteristicas;
    
    @NotBlank @Column(nullable = false)
    private String descricao;
    
    @ManyToOne
    private Categoria categoria;
    
    @NotNull @Column(nullable = false, length = 1000)
    private LocalDateTime dataCadastro = LocalDateTime.now();
    
    @ManyToOne
    private Usuario dono;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Pergunta> perguntas;
    
    @Deprecated
    public Produto() {}
    
    public Produto(String nome, BigDecimal valor, Long quantidade, String descricao, Categoria categoria, Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
    }
    
    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public boolean isDono(Usuario usuario) {
        return this.dono.getId().equals(usuario.getId());
    }

    public void adicionaLinksImagem(List<String> linksImagens) {
        Set<ImagemProduto> imagens = linksImagens.stream().map(l -> new ImagemProduto(l, this))
                .collect(Collectors.toSet());
        
        this.imagens.addAll(imagens);
    }

    public void adicionaOpiniao(Opiniao opiniao) {
        this.opinioes.add(opiniao);        
    }

    public void adicionaPergunta(Pergunta pergunta) {
        this.perguntas.add(pergunta);
    }
    
    public String getNome() {
        return nome;
    }

    public Usuario getDono() {
        return dono;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }
    
    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }
    
    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }
    
    public Integer getNumeroAvaliacoes() {
        return opinioes.size();
    }
    
    public Double getMediaAvaliacoes() {
        Integer numeroAvaliacoes = getNumeroAvaliacoes();
        double somaAvaliacoes = 0;
        
        if (numeroAvaliacoes == 0) {
            return somaAvaliacoes;
        }
        
        for (Opiniao o : this.opinioes) {
            somaAvaliacoes += o.getNota();
        }
        
        return somaAvaliacoes / numeroAvaliacoes;
    }

    public boolean isQuantidadeDisponivel(Integer quantidade) {
        return (quantidadeDisponivel - Long.valueOf(quantidade)) >= 0;
    }
    
    public void diminuiQuantidade(Integer quantidade, ProdutoRepository repository) {
        if (isQuantidadeDisponivel(quantidade)) {
            this.quantidadeDisponivel -= quantidade;
            repository.save(this);
            return;
        }
        
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Quantidade não disponível.");
    }

}
