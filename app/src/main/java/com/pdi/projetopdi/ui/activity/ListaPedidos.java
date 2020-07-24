package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.PedidoAdapter;
import com.pdi.projetopdi.adapter.ProdutoAdapter;
import com.pdi.projetopdi.dao.PedidoDAO;
import com.pdi.projetopdi.modelo.Pedido;

import java.text.ParseException;
import java.util.ArrayList;

public class ListaPedidos extends AppCompatActivity {

    private PedidoAdapter adapter;
    private ArrayList<Pedido> pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pedidos");
        setContentView(R.layout.activity_lista_pedidos);

//        TextView cliente = findViewById(R.id.pedidoCliente);
//        TextView valorTotal = findViewById(R.id.pedidoValorTotal);
        RecyclerView recycler = findViewById(R.id.recyclerPedidos);

        PedidoDAO dao = new PedidoDAO(ListaPedidos.this);
        try {
            dao.inserirPrimeirosDados();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pedidos = dao.buscaPedidos();

        adapter = new PedidoAdapter(ListaPedidos.this, pedidos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }
}
