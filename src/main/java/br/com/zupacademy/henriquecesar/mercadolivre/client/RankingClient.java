package br.com.zupacademy.henriquecesar.mercadolivre.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.henriquecesar.mercadolivre.form.api_externa.NovaCompraRankingForm;

@FeignClient(name = "ranking", url = "http://localhost:8080/api-ranking")
public interface RankingClient {
    
    @PostMapping
    void ranking(@Valid @RequestBody NovaCompraRankingForm form);

}
