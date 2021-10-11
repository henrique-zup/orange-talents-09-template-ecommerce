package br.com.zupacademy.henriquecesar.mercadolivre.service;

import org.springframework.stereotype.Component;

import br.com.zupacademy.henriquecesar.mercadolivre.dto.EmailDTO;

@Component
public class FakerEmailService implements ServicoEmail {

    @Override
    public boolean envia(EmailDTO email) {
        System.out.println(email.toString());
        return true;
    }

}
