package com.pdi.projetopdi.logic;

import android.content.Context;

import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.repository.ProdutoRepository;

import java.math.BigDecimal;

public class EditarProdutoLogic {
    private ProdutoRepository produtoRepository;
    private Produto produto;

    private BigDecimal precoProdutoNovo;
    private String descricaoProdutoNovo;

    public EditarProdutoLogic(Context context) {
        this.produtoRepository = ProdutoRepository.getInstance(context);
    }

    public Produto buscaProdutoPorID(Long idProdutoSelecionado){
        produto = produtoRepository.buscaProdutoPorID(idProdutoSelecionado);
        return produto;
    }

    public void salvarPedidoEditado(String novoPreco, String novaDescricao){
        precoProdutoNovo = new BigDecimal(novoPreco);
        descricaoProdutoNovo = novaDescricao;
        produtoRepository.updateProduto(new Produto(Math.toIntExact(
                produto.getIdproduto()), descricaoProdutoNovo,precoProdutoNovo));
    }

    public void salvarNovoProduto(String novoPreco, String novaDescricao){
        precoProdutoNovo = new BigDecimal(novoPreco);
        descricaoProdutoNovo = novaDescricao;
        produtoRepository.inserirProduto(new Produto(descricaoProdutoNovo,precoProdutoNovo));
    }
}
