package br.com.zupacademy.henriquecesar.mercadolivre.form.gateway;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Transacao;

public interface RetornoTransacao {

    public Transacao toTransacao(Compra compra);

}
