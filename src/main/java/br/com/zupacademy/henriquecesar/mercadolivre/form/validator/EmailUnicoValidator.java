package br.com.zupacademy.henriquecesar.mercadolivre.form.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.henriquecesar.mercadolivre.form.UsuarioForm;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.UsuarioRepository;

@Component
public class EmailUnicoValidator implements Validator {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UsuarioForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        
        UsuarioForm form = (UsuarioForm) target;
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByLogin(form.getLogin());
        
        if (usuario.isPresent()) {
            errors.rejectValue("login", "e-mail já cadastrado");
        }
    }

}
