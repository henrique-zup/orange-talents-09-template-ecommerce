package br.com.zupacademy.henriquecesar.mercadolivre.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransacaoStatus status;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @NotBlank
    @Column(unique = true)
    private String idTransacaoGateway;

    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(@NotNull TransacaoStatus status, @NotBlank String idTransacaoGateway,
            Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
    }
    
    public boolean isBemSucedida() {
        return this.status.equals(TransacaoStatus.SUCESSO);
    }

}
