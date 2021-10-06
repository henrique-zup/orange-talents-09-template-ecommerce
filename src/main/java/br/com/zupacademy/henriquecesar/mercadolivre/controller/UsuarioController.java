package br.com.zupacademy.henriquecesar.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henriquecesar.mercadolivre.form.UsuarioForm;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping
    public void cadastraUsuario(@Valid @RequestBody UsuarioForm form) {
        Usuario usuario = form.toModel(passwordEncoder);
        usuarioRepository.save(usuario);
    }

}
