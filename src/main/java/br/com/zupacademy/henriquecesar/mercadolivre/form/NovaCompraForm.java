package br.com.zupacademy.henriquecesar.mercadolivre.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.MetodoPagamento;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;

public class NovaCompraForm {
    
    @NotNull
    @Positive
    private Integer quantidade;
    
    @NotNull
    private MetodoPagamento metodoPagamento;

    public Integer getQuantidade() {
        return quantidade;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }
    
    public Compra toModel(Usuario usuario, Produto produto) {
        return new Compra(produto, usuario, quantidade, metodoPagamento);
    }

}
