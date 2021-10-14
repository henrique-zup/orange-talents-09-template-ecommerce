package br.com.zupacademy.henriquecesar.mercadolivre.form.gateway;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.TransacaoStatus;

public enum TransacaoStatusPaypal {
    
    ERRO,
    SUCESSO;
    
    TransacaoStatus toTransacaoStatus() {
        if (this.equals(ERRO)) {
            return TransacaoStatus.ERRO;
        }
        return TransacaoStatus.SUCESSO;
    }

}
