package br.com.zupacademy.henriquecesar.mercadolivre.service.pagamento;

import java.net.URI;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;

public interface ServicoPagamento {

    public URI processar(Compra compra);

}
