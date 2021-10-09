package br.com.zupacademy.henriquecesar.mercadolivre.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.henriquecesar.mercadolivre.form.validator.ExisteId;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Categoria;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.CategoriaRepository;

public class NovoProdutoForm {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private Long quantidadeDisponivel;

    @NotNull
    private BigDecimal valor;

    @Size(min = 3)
    private List<NovaCaracteristicaForm> caracteristicas;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @ExisteId(entityClass = Categoria.class)
    private Long categoriaId;

    public String getNome() {
        return nome;
    }

    public Long getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public List<NovaCaracteristicaForm> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario dono) {
        Categoria categoria = categoriaRepository.findById(categoriaId).get();
        
        Produto produto = new Produto(nome, valor, quantidadeDisponivel, descricao, categoria, dono);
        produto.setCaracteristicas(caracteristicas.stream()
                .map(c -> c.toModel(produto)).collect(Collectors.toList()));
        
        return produto;
    }

}
