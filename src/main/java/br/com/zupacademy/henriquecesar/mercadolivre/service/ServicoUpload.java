package br.com.zupacademy.henriquecesar.mercadolivre.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ServicoUpload {
    
    public List<String> envia(List<MultipartFile> arquivos);

}
