package br.com.zupacademy.henriquecesar.mercadolivre.modelo;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.henriquecesar.mercadolivre.service.pagamento.ServicoPagamento;
import br.com.zupacademy.henriquecesar.mercadolivre.service.pagamento.ServicoPagamentoFactory;

@Entity
public class Compra {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario comprador;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private BigDecimal precoUnitario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CompraStatus status = CompraStatus.INICIADA;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    @NotNull
    private LocalDateTime data = LocalDateTime.now();

    @Deprecated
    public Compra() {
    }

    public Compra(Produto produto, Usuario comprador, @Positive Integer quantidade, MetodoPagamento metodoPagamento) {
        this.produto = produto;
        this.comprador = comprador;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getValor();
        this.metodoPagamento = metodoPagamento;
    }

    public UUID getId() {
        return id;
    }
    
    public Usuario getComprador() {
        return comprador;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public URI processarPagamento() {
        ServicoPagamento servicoPagamento = ServicoPagamentoFactory.getServicoPagamento(metodoPagamento);

        URI uri = servicoPagamento.processar(this);

        if (Objects.isNull(uri)) {
            // Trata falha no processamento
        }

        return uri;
    }

    public void adicionaTransacao(Transacao transacao) {
        if (this.transacoes.contains(transacao)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Essa transação já foi processada.");    
        }
        
        if (hasTransacaoBemSucedida()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Essa compra já possui uma transaçào processada com sucesso.");
        }

        this.transacoes.add(transacao);
    }

    public boolean hasTransacaoBemSucedida() {
        return this.transacoes.stream().filter(Transacao::isBemSucedida).collect(Collectors.toSet()).size() > 0;
    }

}
