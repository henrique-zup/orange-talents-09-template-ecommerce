package br.com.zupacademy.henriquecesar.mercadolivre.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.henriquecesar.mercadolivre.form.api_externa.NovaCompraNFForm;

@FeignClient(name = "nf", url = "http://localhost:8080/api-nota-fiscal")
public interface NotaFiscalClient {
    
    @PostMapping
    void criaNota(@Valid @RequestBody NovaCompraNFForm form);

}
