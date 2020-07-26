package com.pdi.projetopdi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.PedidoAdapter;
import com.pdi.projetopdi.dao.PedidoDAO;
import com.pdi.projetopdi.modelo.Pedido;

import java.text.ParseException;
import java.util.ArrayList;

public class ListaPedidosActivity extends AppCompatActivity {

    private PedidoAdapter adapter;
    private ArrayList<Pedido> pedidos;

    private Button btNovoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pedidos");
        setContentView(R.layout.activity_lista_pedidos);

        RecyclerView recycler = findViewById(R.id.recyclerPedidos);
        btNovoPedido = findViewById(R.id.btNovoPedido);

        PedidoDAO pedidoDAO = new PedidoDAO(ListaPedidosActivity.this);
        try {
            pedidoDAO.inserirPrimeirosDadosPedido();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pedidos = pedidoDAO.buscaPedidos();

        adapter = new PedidoAdapter(ListaPedidosActivity.this, pedidos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);

        acaoBotaoNovoPedido();

    }

    public void acaoBotaoNovoPedido(){
        btNovoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPedidosActivity.this,NovoPedidoActivity.class));
            }
        });
    }
}
