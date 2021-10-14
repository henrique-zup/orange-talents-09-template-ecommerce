package br.com.zupacademy.henriquecesar.mercadolivre.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.henriquecesar.mercadolivre.client.NotaFiscalClient;
import br.com.zupacademy.henriquecesar.mercadolivre.client.RankingClient;
import br.com.zupacademy.henriquecesar.mercadolivre.form.api_externa.NovaCompraNFForm;
import br.com.zupacademy.henriquecesar.mercadolivre.form.api_externa.NovaCompraRankingForm;
import br.com.zupacademy.henriquecesar.mercadolivre.form.gateway.RetornoGatewayPagSeguroForm;
import br.com.zupacademy.henriquecesar.mercadolivre.form.gateway.RetornoGatewayPaypalForm;
import br.com.zupacademy.henriquecesar.mercadolivre.form.gateway.RetornoTransacao;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Transacao;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.henriquecesar.mercadolivre.service.EmailManager;

@RestController
public class RetornoTransacaoController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private NotaFiscalClient nfClient;

    @Autowired
    private RankingClient rankingClient;

    @Autowired
    private EmailManager emailManager;

    @PostMapping("/retorno-paypal/{idCompra}")
    public void retornoTransacaoPaypal(@PathVariable UUID idCompra, @RequestBody RetornoGatewayPaypalForm form) {
        processamento(idCompra, form);
    }

    @PostMapping("/retorno-pagseguro/{idCompra}")
    public void retornoTransacaoPagSeguro(@PathVariable UUID idCompra, @RequestBody RetornoGatewayPagSeguroForm form) {
        processamento(idCompra, form);
    }

    private void processamento(UUID idCompra, RetornoTransacao form) {
        Optional<Compra> _compra = compraRepository.findById(idCompra);
        if (_compra.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Compra compra = _compra.get();
        Transacao transacao = form.toTransacao(compra);
        compra.adicionaTransacao(transacao);
        compraRepository.save(compra);

        posProcessamento(compra);
    }

    private void posProcessamento(Compra compra) {
        if (compra.hasTransacaoBemSucedida()) {
            NovaCompraNFForm formNota = new NovaCompraNFForm(compra.getId(), compra.getProduto().getDono().getId());
            nfClient.criaNota(formNota);

            NovaCompraRankingForm formRanking = new NovaCompraRankingForm(compra.getId(),
                    compra.getComprador().getId());
            rankingClient.ranking(formRanking);

            emailManager.pagamentoAprovadoComprador(compra);
        } else {
            emailManager.pagamentoRecusadoComprador(compra);
        }

    }

}
