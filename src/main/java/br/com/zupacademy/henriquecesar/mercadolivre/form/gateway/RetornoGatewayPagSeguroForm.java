package br.com.zupacademy.henriquecesar.mercadolivre.form.gateway;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.henriquecesar.mercadolivre.form.validator.ValorUnico;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Transacao;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.TransacaoStatus;

public class RetornoGatewayPagSeguroForm implements RetornoTransacao {

    @Range(min = 0, max = 1)
    private int status;

    @NotBlank
    @ValorUnico(entityClass = Transacao.class,  field = "id_transacao_gateway")
    private String idTransacao;

    @Override
    public Transacao toTransacao(Compra compra) {
        TransacaoStatus status = (this.status == 0) ? TransacaoStatus.ERRO : TransacaoStatus.SUCESSO;
        return new Transacao(status, idTransacao, compra);
    }

    public int getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

}
