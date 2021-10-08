package br.com.zupacademy.henriquecesar.mercadolivre.config;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;

public class UsuarioLogado implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    private Usuario usuario;
    private User userDetails;
    
    public UsuarioLogado(@NotNull Usuario usuario) {
        this.usuario = usuario;
        this.userDetails = new User(usuario.getLogin(), usuario.getSenha(), List.of());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetails.isEnabled();
    }

    public Long getId() {
        return usuario.getId();
    }

}
