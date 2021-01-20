package com.pdi.projetopdi.logic;

import android.content.Context;

import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.repository.ProdutoRepository;

import java.util.ArrayList;

public class ListaProdutosLogic  {

    private ProdutoRepository produtoRepository;

    public ListaProdutosLogic(Context context){
        produtoRepository =  produtoRepository.getInstance(context);
    }

    public ArrayList<Produto> buscarProdutoDigtado(String descricaoProduto){
        ArrayList<Produto> listaProdutos = produtoRepository.buscaProdutoDescricao(descricaoProduto);
        return listaProdutos;
    }


    public ArrayList<Produto> buscaProdutos() {
        ArrayList<Produto> listaProdutos = produtoRepository.buscaProdutos();
        return listaProdutos;
    }
}
