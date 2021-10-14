package br.com.zupacademy.henriquecesar.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henriquecesar.mercadolivre.form.api_externa.NovaCompraNFForm;
import br.com.zupacademy.henriquecesar.mercadolivre.form.api_externa.NovaCompraRankingForm;

@RestController
public class ServicosExternosController {

    @PostMapping(value = "/api-nota-fiscal")
    public void criaNota(@Valid @RequestBody NovaCompraNFForm form) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Nota Fiscal para a compra %s foi gerada.".replace("%s", form.getIdCompra().toString()));
    }

    @PostMapping(value = "/api-ranking")
    public void ranking(@Valid @RequestBody NovaCompraRankingForm form) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Ranking atualizado -> vendedor ID. %d".replace("%d", form.getIdDonoProduto().toString()));
    }

}
