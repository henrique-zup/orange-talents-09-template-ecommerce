package br.com.zupacademy.henriquecesar.mercadolivre.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;

public class UsuarioForm {
    
    @NotBlank @Email
    private String login;
    
    @NotNull @Size(min = 6)
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
    
    public Usuario toModel(PasswordEncoder passwordEncoder) {
        String senhaCodificada = passwordEncoder.encode(this.senha);
        LocalDateTime dataCadastro = LocalDateTime.now();
        return new Usuario(login, senhaCodificada, dataCadastro);
    }

}
