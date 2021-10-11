package br.com.zupacademy.henriquecesar.mercadolivre.response;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.ImagemProduto;

public class ImagemProdutoResponse {

    @NotBlank
    private String link;

    public ImagemProdutoResponse(ImagemProduto imagemProduto) {
        this.link = imagemProduto.getLink();
    }

    public String getLink() {
        return link;
    }

}
