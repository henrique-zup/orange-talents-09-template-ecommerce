package br.com.zupacademy.henriquecesar.mercadolivre.service.pagamento;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.MetodoPagamento;

public class ServicoPagamentoFactory {
    
    public static ServicoPagamento getServicoPagamento(MetodoPagamento metodoPagamento) {
        switch (metodoPagamento) {
        case PAYPAL:
            return new GatewayPaypal();
        case PAGSEGURO:
            return new GatewayPagSeguro();
        default:
            return null;
        }
    }

}
