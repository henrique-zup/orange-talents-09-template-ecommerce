package br.com.zupacademy.henriquecesar.mercadolivre.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        
        if (usuario.isPresent()) {
            return new UsuarioLogado(usuario.get());
        }
        
        throw new UsernameNotFoundException("Dados inválidos.");
    }

}
