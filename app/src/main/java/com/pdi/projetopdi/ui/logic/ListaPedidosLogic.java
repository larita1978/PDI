package com.pdi.projetopdi.ui.logic;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.adapter.PedidoAdapter;
import com.pdi.projetopdi.model.Pedido;
import com.pdi.projetopdi.repository.PedidoRepository;

import java.util.ArrayList;

public class ListaPedidosLogic {

    private final Context context;
    private PedidoRepository pedidoRepository;
    private PedidoAdapter adapter;
    private ArrayList<Pedido> pedidos;
    private RecyclerView recycler;

    public ListaPedidosLogic(Context context, RecyclerView recycler) {
        this.context = context;
        this.pedidoRepository = PedidoRepository.getInstance(context);
        this.recycler = recycler;
    }

    public void exibirPedidos(){
        pedidos = pedidoRepository.buscaPedidos();

        adapter = new PedidoAdapter(context, pedidos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

}
