package br.com.zupacademy.henriquecesar.mercadolivre.form;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Pergunta;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;

public class NovaPerguntaProdutoForm {
    
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public Pergunta toModel(Usuario usuario, Produto produto) {
        return new Pergunta(titulo, usuario, produto);
    }

}
