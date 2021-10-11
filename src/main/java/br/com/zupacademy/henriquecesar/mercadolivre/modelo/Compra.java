package br.com.zupacademy.henriquecesar.mercadolivre.modelo;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;

import br.com.zupacademy.henriquecesar.mercadolivre.service.pagamento.ServicoPagamento;
import br.com.zupacademy.henriquecesar.mercadolivre.service.pagamento.ServicoPagamentoFactory;

@Entity
public class Compra {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
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

    public URI processarPagamento() {
        ServicoPagamento servicoPagamento = ServicoPagamentoFactory.getServicoPagamento(metodoPagamento);

        URI uri = servicoPagamento.processar(this);

        if (Objects.isNull(uri)) {
            // Trata falha no processamento
        }

        return uri;
    }

}
