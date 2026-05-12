package com.humbertoff.github.produtos.service;

import com.humbertoff.github.produtos.model.Produto;
import com.humbertoff.github.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> obterProdutoPorCodigo(Long codigo) {
        return produtoRepository.findById(codigo);
    }
}
