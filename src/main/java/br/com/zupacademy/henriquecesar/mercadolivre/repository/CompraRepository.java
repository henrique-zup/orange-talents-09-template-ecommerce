package br.com.zupacademy.henriquecesar.mercadolivre.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;

public interface CompraRepository extends CrudRepository<Compra, UUID> {

}
