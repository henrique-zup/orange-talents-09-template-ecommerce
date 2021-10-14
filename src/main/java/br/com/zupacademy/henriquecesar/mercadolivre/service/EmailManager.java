package br.com.zupacademy.henriquecesar.mercadolivre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zupacademy.henriquecesar.mercadolivre.dto.EmailDTO;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;
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

    public boolean pagamentoAprovadoComprador(Compra compra) {
        EmailDTO emailDTO = new EmailDTO(
                compra.getComprador().getLogin(),
                "Pagamento autorizado, produto: " + compra.getProduto().getNome(),
                "<<template da mensagem>>"
        );
        
        return emailService.envia(emailDTO);
        
    }

    public boolean pagamentoRecusadoComprador(Compra compra) {
        EmailDTO emailDTO = new EmailDTO(
                compra.getComprador().getLogin(),
                "Pagamento recusado, produto: " + compra.getProduto().getNome(),
                "<<template da mensagem>>"
        );
        
        return emailService.envia(emailDTO);
        
    }
    
}
