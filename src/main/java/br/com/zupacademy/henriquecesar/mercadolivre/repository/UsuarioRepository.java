package br.com.zupacademy.henriquecesar.mercadolivre.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
