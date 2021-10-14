package br.com.zupacademy.henriquecesar.mercadolivre.form.gateway;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.henriquecesar.mercadolivre.form.validator.ValorUnico;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Transacao;

public class RetornoGatewayPaypalForm implements RetornoTransacao {

    private TransacaoStatusPaypal status;

    @NotBlank
    @ValorUnico(entityClass = Transacao.class,  field = "id_transacao_gateway")
    private String idTransacao;

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.toTransacaoStatus(), idTransacao, compra);
    }

    public TransacaoStatusPaypal getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

}
