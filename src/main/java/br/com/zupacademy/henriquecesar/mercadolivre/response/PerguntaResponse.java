package br.com.zupacademy.henriquecesar.mercadolivre.response;

import java.time.LocalDateTime;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Pergunta;

public class PerguntaResponse {

    private String titulo;

    private LocalDateTime data;

    private UsuarioResponse usuario;

    public PerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.data = pergunta.getData();
        this.usuario = new UsuarioResponse(pergunta.getUsuario());
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }
}
