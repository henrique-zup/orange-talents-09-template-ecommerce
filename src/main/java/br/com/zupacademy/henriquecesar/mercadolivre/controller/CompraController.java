package br.com.zupacademy.henriquecesar.mercadolivre.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.henriquecesar.mercadolivre.config.UsuarioLogado;
import br.com.zupacademy.henriquecesar.mercadolivre.form.NovaCompraForm;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Compra;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.henriquecesar.mercadolivre.service.EmailManager;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EmailManager emailManager;

    @PostMapping("/{idProduto}")
    public ResponseEntity<Void> novaCompra(@PathVariable Long idProduto, @RequestBody @Valid NovaCompraForm form,
            @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

        Usuario comprador = usuarioLogado.getUsuario();
        Optional<Produto> _produto = produtoRepository.findById(idProduto);

        if (_produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado.");
        }

        Produto produto = _produto.get();

        if (!produto.isQuantidadeDisponivel(form.getQuantidade())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Quantidade não disponível.");
        }

        produto.diminuiQuantidade(form.getQuantidade(), produtoRepository);
        Compra compra = form.toModel(comprador, produto);
        compraRepository.save(compra);

        URI uri = compra.processarPagamento();

        emailManager.novaCompraParaVendedor(produto);

        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();

    }
}
