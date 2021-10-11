package br.com.zupacademy.henriquecesar.mercadolivre.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDTO {
    
    @Email
    private String destinatario;
    
    @NotBlank
    private String assunto;
    
    @NotBlank
    private String mensagem;

    public EmailDTO(@Email String destinatario, @NotBlank String assunto, @NotBlank String mensagem) {
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }
    
    @Override
    public String toString() {
        return  "[E-MAIL]"
                + "\nDe: MercadoLivre <no-reply@mercadolivre.com.br>"
                + "\nData: " + LocalDateTime.now().toString()
                + "\nAssunto: " + assunto
                + "\nPara: " + destinatario
                + "\nMensagem: " + mensagem;
    }

}
