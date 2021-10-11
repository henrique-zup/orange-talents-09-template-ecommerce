package br.com.zupacademy.henriquecesar.mercadolivre.response;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Opiniao;

public class OpiniaoResponse {

    private Integer nota;

    private String titulo;

    private String descricao;

    private UsuarioResponse usuario;

    public OpiniaoResponse(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usuario = new UsuarioResponse(opiniao.getUsuario());
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

}
