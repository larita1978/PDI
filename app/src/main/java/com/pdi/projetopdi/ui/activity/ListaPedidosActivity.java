package com.pdi.projetopdi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.ui.logic.ListaPedidosLogic;

public class ListaPedidosActivity extends AppCompatActivity {

    private RecyclerView recycler;

    private ListaPedidosLogic listaPedidosLogic;

    private Button btNovoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pedidos");
        setContentView(R.layout.activity_lista_pedidos);

    }

    @Override
    protected void onStart() {
        super.onStart();
        recycler = findViewById(R.id.recyclerPedidos);
        btNovoPedido = findViewById(R.id.btNovoPedido);

        listaPedidosLogic = new ListaPedidosLogic(this,recycler);
        listaPedidosLogic.exibirPedidos();
        acaoBotaoNovoPedido();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    public void exibirPedidos(){
//        pedidos = pedidoRepository.buscaPedidos();
//
//        adapter = new PedidoAdapter(ListaPedidosActivity.this, pedidos);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
//
//        recycler.setLayoutManager(layoutManager);
//        recycler.setAdapter(adapter);
//    }

    public void acaoBotaoNovoPedido(){
        btNovoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPedidosActivity.this, NovoPedidoActivity.class));
            }
        });
    }
}
