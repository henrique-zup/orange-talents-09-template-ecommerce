package br.com.zupacademy.henriquecesar.mercadolivre.form.api_externa;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class NovaCompraNFForm {

    @NotNull
    private UUID idCompra;
    
    @NotNull
    private Long idCliente;

    public UUID getIdCompra() {
        return idCompra;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public NovaCompraNFForm(@NotNull UUID idCompra, @NotNull Long idCliente) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
    }
    

}
