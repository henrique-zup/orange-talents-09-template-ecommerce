package br.com.zupacademy.henriquecesar.mercadolivre.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FakerUploadService implements ServicoUpload {

    @Override
    public List<String> envia(List<MultipartFile> arquivos) {
        return arquivos.stream().map(this::generateLink).collect(Collectors.toList());
    }
    
    private String generateLink(MultipartFile file) {
        return "https://fakeuploadservice.com.br/%s-%d.png"
                .replace("%s", file.getOriginalFilename())
                .replace("%d", String.valueOf(new Random().nextInt(5000)));
    }

}
