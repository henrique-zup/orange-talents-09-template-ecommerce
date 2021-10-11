package br.com.zupacademy.henriquecesar.mercadolivre.response;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;

public class UsuarioResponse {
    
    @NotBlank
    private String login;

    public String getLogin() {
        return login;
    }
    
    public UsuarioResponse(Usuario usuario) {
        this.login = usuario.getLogin();
    }

}
