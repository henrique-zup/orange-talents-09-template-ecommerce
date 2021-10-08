package br.com.zupacademy.henriquecesar.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henriquecesar.mercadolivre.config.TokenManager;
import br.com.zupacademy.henriquecesar.mercadolivre.form.LoginForm;
import br.com.zupacademy.henriquecesar.mercadolivre.response.TokenResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private TokenManager tokenManager;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenManager.generateToken(authentication);
            
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
            
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
            
        }
        
    }

}
