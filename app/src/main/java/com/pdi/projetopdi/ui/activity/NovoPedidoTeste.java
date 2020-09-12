package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.PedidoItensAdapter;
import com.pdi.projetopdi.dao.PedidoDAO;
import com.pdi.projetopdi.fragments.DialogEditarProdutoPedido;
import com.pdi.projetopdi.modelo.Pedido;
import com.pdi.projetopdi.modelo.PedidoItem;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NovoPedidoTeste extends AppCompatActivity {

    private PedidoItensAdapter adapter;
    private RecyclerView recycler;
    private ArrayList<PedidoItem> pedidoItemList;
    private PedidoItem pedidoItem;

    private TextView dataPedido;
    private EditText nomeCliente;
    private EditText enderecoCliente;

    private TextView valorTotalProdutos;
    private TextView valorTotalItens;
    private TextView valorTotal;

    private Button btAddProduto;
    private Button btExibirCarrinho;
    private LinearLayout linearteste;
    private Boolean show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido_teste);

        long date1 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        btExibirCarrinho = findViewById(R.id.btExibirCarrinho);
        linearteste = findViewById(R.id.idLinearTeste);

        dataPedido = findViewById(R.id.dataPedido);
        nomeCliente = findViewById(R.id.idNomeCliente);
        enderecoCliente = findViewById(R.id.idEnderecoCliente);


        btAddProduto = findViewById(R.id.btAddProduto);
        recycler = findViewById(R.id.recyclerProdutosCabecalho);


        valorTotalProdutos = findViewById(R.id.idTextResumoTotalProdutos);
        valorTotalItens = findViewById(R.id.idTextResumoTotalItens);
        valorTotal = findViewById(R.id.idTextResumoValorTotal);

        PedidoDAO pedidodao = new PedidoDAO(this);
        Pedido pedido = new Pedido();

        pedido.setCliente(nomeCliente.getText().toString());
        pedido.setEndereco(enderecoCliente.getText().toString());
        String data2 = sdf.format(date1);
//        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        dataPedido.setText(data2);
//        nomeCliente.setText("LArissa");




        pedidoItemList = new ArrayList<>();
        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));
        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));
        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));

        exibirProdutos();
            btAddProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DialogEditarProdutoPedido().show(getSupportFragmentManager(),"teste");
                }
            });


//        Intent intent = new Intent(getActivity(), NovoPedidoActivity.class);
//        intent.putExtra("dados",pedidoItemList);
//        getActivity().startActivity(intent);

        show = true;
        btExibirCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show){
                    linearteste.setVisibility(View.INVISIBLE);
                    show =false;
                }else{
                    linearteste.setVisibility(View.VISIBLE);
                    show =true;
                }
            }
        });

    }
    public void exibirProdutos(){
        adapter = new PedidoItensAdapter(this, pedidoItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }
}
