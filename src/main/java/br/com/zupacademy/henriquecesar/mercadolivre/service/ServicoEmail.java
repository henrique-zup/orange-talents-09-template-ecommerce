package br.com.zupacademy.henriquecesar.mercadolivre.service;

import br.com.zupacademy.henriquecesar.mercadolivre.dto.EmailDTO;

public interface ServicoEmail {
    
    public boolean envia(EmailDTO email);

}
