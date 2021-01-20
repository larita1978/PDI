package com.pdi.projetopdi.logic;

import android.content.Context;

import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.repository.ProdutoRepository;

import java.math.BigDecimal;

public class EditarProdutoLogic {
    private ProdutoRepository produtoRepository;
    private Produto produto;

    public EditarProdutoLogic(Context context) {
        this.produtoRepository = ProdutoRepository.getInstance(context);
    }

    public Produto buscaProdutoPorID(Long idProdutoSelecionado){
        produto = produtoRepository.buscaProdutoPorID(idProdutoSelecionado);
        return produto;
    }

    public void salvarPedidoEditado(String novoPreco, String novaDescricao){
        BigDecimal novoPrdPrecoD = new BigDecimal(novoPreco);
        String novoPrdDescricaoD = novaDescricao;
        produtoRepository.updateProduto(new Produto(Math.toIntExact(produto.getIdproduto()),novoPrdDescricaoD,novoPrdPrecoD));
    }

    public void salvarNovoProduto(String novoPreco, String novaDescricao){
        BigDecimal novoPrdPrecoD = new BigDecimal(novoPreco);
        String novoPrdDescricaoD = novaDescricao;
        produtoRepository.inserirProduto(new Produto(novoPrdDescricaoD,novoPrdPrecoD));
    }
}
