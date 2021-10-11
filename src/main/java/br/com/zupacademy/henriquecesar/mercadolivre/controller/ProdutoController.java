package br.com.zupacademy.henriquecesar.mercadolivre.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.henriquecesar.mercadolivre.config.UsuarioLogado;
import br.com.zupacademy.henriquecesar.mercadolivre.form.NovaOpiniaoProdutoForm;
import br.com.zupacademy.henriquecesar.mercadolivre.form.NovasImagensProdutoForm;
import br.com.zupacademy.henriquecesar.mercadolivre.form.NovoProdutoForm;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Produto;
import br.com.zupacademy.henriquecesar.mercadolivre.modelo.Usuario;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.henriquecesar.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.henriquecesar.mercadolivre.service.FakerUploadService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private FakerUploadService fakerUploadService;

    @PostMapping
    public void cadastrarProduto(@RequestBody @Valid NovoProdutoForm form,
            @AuthenticationPrincipal UsuarioLogado usuario) {
        Usuario dono = usuarioRepository.findById(usuario.getId()).get();
        Produto produto = form.toModel(categoriaRepository, dono);
        produtoRepository.save(produto);

    }

    @PostMapping("/{idProduto}/imagens")
    public void adicionaImagens(@PathVariable Long idProduto, @Valid NovasImagensProdutoForm form,
            @AuthenticationPrincipal UsuarioLogado usuario) {
        
        Usuario solicitante = usuarioRepository.findById(usuario.getId()).get();
        Optional<Produto> _produto = produtoRepository.findById(idProduto);
        
        if (_produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Produto produto = _produto.get();
        
        if (!produto.isDono(solicitante)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        
        List<String> linksImagens = fakerUploadService.envia(form.getImagens());
        produto.adicionaLinksImagem(linksImagens);
        
        produtoRepository.save(produto);
    }
    
    @PostMapping("/{idProduto}/opinioes")
    public void publicarOpiniao(@PathVariable Long idProduto, @RequestBody @Valid NovaOpiniaoProdutoForm form, 
            @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        
        Usuario usuario = usuarioRepository.findById(usuarioLogado.getId()).get();
        Optional<Produto> _produto = produtoRepository.findById(idProduto);
        
        if (_produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Produto produto = _produto.get();
        
        if (!usuario.comprouProduto(produto)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        
        Opiniao opiniao = form.toModel(usuario, produto);
        produto.adicionaOpiniao(opiniao);
        
        produtoRepository.save(produto);
    }

}
