package br.com.zupacademy.henriquecesar.mercadolivre.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovasImagensProdutoForm {
    
    @NotNull @Size(min = 1)
    private List<MultipartFile> imagens;

    public List<MultipartFile> getImagens() {
        return imagens;
    }
    
    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

}
