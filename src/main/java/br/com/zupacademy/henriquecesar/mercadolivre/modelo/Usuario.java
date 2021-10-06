package br.com.zupacademy.henriquecesar.mercadolivre.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String login;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(nullable = false)
    private LocalDateTime dataCadastro;
    
    @Deprecated
    public Usuario() {
    }
    
    public Usuario(@NotBlank @Email String login, @NotBlank String senha, @NotNull LocalDateTime dataCadastro) {
        this.login = login;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
    }    

}
