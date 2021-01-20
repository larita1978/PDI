package com.pdi.projetopdi.logic;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.adapter.ProdutoAdapter;
import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.repository.ProdutoRepository;
import com.pdi.projetopdi.ui.activity.ListaProdutosActivity;

import java.util.ArrayList;

public class ListaProdutosLogic  {

    private RecyclerView recycler;
    private ProdutoAdapter adapter;
    private Context context;

    private ProdutoRepository produtoRepository;

    public ListaProdutosLogic(Context context, RecyclerView recycler){
        produtoRepository =  produtoRepository.getInstance(context);
        this.recycler = recycler;
        this.context=context;
    }

    public ArrayList<Produto> buscarProdutoDigtado(String descricaoProduto){
        ArrayList<Produto> listaProdutos = produtoRepository.buscaProdutoDescricao(descricaoProduto);
        return listaProdutos;
    }


    public ArrayList<Produto> buscaProdutos() {
        ArrayList<Produto> listaProdutos = produtoRepository.buscaProdutos();
        return listaProdutos;
    }

    public void exibirProdutos(ArrayList<Produto> listaProdutos){

        adapter = new ProdutoAdapter(context, listaProdutos);
        GridLayoutManager layoutManager = new GridLayoutManager(context,2);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }
}
