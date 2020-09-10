package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.PedidoItensAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido_teste);

        long date1 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");


        TextView dataPedido = findViewById(R.id.dataPedido);
        EditText nomeCliente = findViewById(R.id.idNomeCliente);
        EditText enderecoCliente = findViewById(R.id.idEnderecoCliente);

//        PedidoDAO pedidodao = new PedidoDAO(this);
        Pedido pedido = new Pedido();

//        pedido.setCliente(nomeCliente.getText().toString());
//        pedido.setEndereco(enderecoCliente.getText().toString());
        String data2 = sdf.format(date1);
//        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        dataPedido.setText(data2);
//        nomeCliente.setText("LArissa");


            Button btAddProduto = findViewById(R.id.btAddProduto);
            recycler = findViewById(R.id.recyclerProdutosCabecalho);

            pedidoItemList = new ArrayList<PedidoItem>();
            pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));

            exibirProdutos();
            btAddProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    DialogFragment dialog = DialogEditarProdutoPedido.newInstance();
//                    dialog.show(getFragmentManager(),"tag");
                }
            });


//        Intent intent = new Intent(getActivity(), NovoPedidoActivity.class);
//        intent.putExtra("dados",pedidoItemList);
//        getActivity().startActivity(intent);


    }
    public void exibirProdutos(){
        adapter = new PedidoItensAdapter(this, pedidoItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }
}
