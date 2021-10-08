package br.com.zupacademy.henriquecesar.mercadolivre.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
