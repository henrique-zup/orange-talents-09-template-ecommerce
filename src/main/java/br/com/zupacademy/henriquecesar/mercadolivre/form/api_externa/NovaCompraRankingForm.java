package br.com.zupacademy.henriquecesar.mercadolivre.form.api_externa;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class NovaCompraRankingForm {
    
    @NotNull
    private UUID idCompra;
    
    @NotNull
    private Long idDonoProduto;

    public UUID getIdCompra() {
        return idCompra;
    }

    public Long getIdDonoProduto() {
        return idDonoProduto;
    }

    public NovaCompraRankingForm(@NotNull UUID idCompra, @NotNull Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }
}
