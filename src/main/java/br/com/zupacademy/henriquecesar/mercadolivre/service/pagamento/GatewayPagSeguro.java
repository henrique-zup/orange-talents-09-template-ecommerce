package br.com.zupacademy.henriquecesar.mercadolivre.service.pagamento;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;

public class GatewayPagSeguro implements ServicoPagamento {

    private final String BASE_URL = "https://pagseguro.com/";
    private final String REDIRECT_URL = "https://mercadolivre.com.br/compras/";

    @Override
    public URI processar(Compra compra) {
        StringBuilder uri = new StringBuilder(BASE_URL)
                .append("buyerId=").append(compra.getId())
                .append("&redirectUrl=").append(REDIRECT_URL);
        
        try {
            return new URI(uri.toString());
            
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
