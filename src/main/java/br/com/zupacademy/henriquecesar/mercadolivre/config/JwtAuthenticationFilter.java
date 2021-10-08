package br.com.zupacademy.henriquecesar.mercadolivre.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.UsuarioRepository;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private TokenManager tokenManager;
    private UsuarioRepository usuarioRepository;
    
    public JwtAuthenticationFilter(TokenManager tokenManager, UsuarioRepository usuarioRepository) {
        super();
        this.tokenManager = tokenManager;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = recuperarToken(request);
        boolean valid = tokenManager.isValidToken(token);
        
        if (valid) {
            autenticarCliente(token);
        }
        
        filterChain.doFilter(request, response);
        
    }

    private void autenticarCliente(String token) {
        Long userId = tokenManager.getUserId(token);
        Usuario usuario =  usuarioRepository.findById(userId).get();
        UsuarioLogado usuarioLogado = new UsuarioLogado(usuario);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuarioLogado, null, usuarioLogado.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }
        
        return token.substring(7, token.length());
    }

}
