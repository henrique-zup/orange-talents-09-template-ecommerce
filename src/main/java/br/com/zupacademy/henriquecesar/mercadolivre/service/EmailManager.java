package br.com.zupacademy.henriquecesar.mercadolivre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zupacademy.henriquecesar.mercadolivre.dto.EmailDTO;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;

@Service
public class EmailManager {

    @Autowired
    private FakerEmailService emailService;
    
    public boolean novaCompraParaVendedor(Produto produto) {
        
        EmailDTO emailDTO = new EmailDTO(
                produto.getDono().getLogin(),
                "Nova compra no Mercado Livre, produto: " + produto.getNome(),
                "<<template da mensagem>>"
        );
        
        return emailService.envia(emailDTO);
    }
    
}
