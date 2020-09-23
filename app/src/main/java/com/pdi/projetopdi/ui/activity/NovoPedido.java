package com.pdi.projetopdi.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.PedidoItensAdapter;
import com.pdi.projetopdi.dao.PedidoDAO;
import com.pdi.projetopdi.fragments.DialogEditarProdutoPedido;
import com.pdi.projetopdi.modelo.Pedido;
import com.pdi.projetopdi.modelo.PedidoItem;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class NovoPedido extends FragmentActivity {

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

    private Button btGravarPedido;

    private Button btAddProduto;
    private Button btExibirCarrinho;
    private LinearLayout linearteste;
    private Boolean show;

    private String data2;

    private BigDecimal totalQuantidade;
    private BigDecimal totalItens;
    private BigDecimal valorTotalPedido;

    private SharedPreferences idUsuarioLogado;
    private int idUsuarioLogadoLong;

    private Pedido novoPedido;
    private PedidoDAO pedidoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        idUsuarioLogado = PreferenceManager.getDefaultSharedPreferences(this);
        idUsuarioLogadoLong = idUsuarioLogado.getInt("login",0);
        Log.i("idLogin", String.valueOf(idUsuarioLogadoLong));

        show = true;

        long date1 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);

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

        btGravarPedido = findViewById(R.id.btGravarPedido);

        pedidoDAO = new PedidoDAO(this);
        novoPedido = new Pedido();

        totalQuantidade = BigDecimal.ZERO;
        totalItens =BigDecimal.ZERO;
        valorTotalPedido = BigDecimal.ZERO;

        data2 = sdf.format(date1);
//        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        dataPedido.setText(data2);
//        nomeCliente.setText("LArissa");


        pedidoItemList = new ArrayList<>();
        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));
        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));
        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));

        exibirProdutos();
        clicouBotaoAddProduto();
        exibirProdutosCarrinho();

        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));
        exibirProdutos();

        calculoValoresResumo();


        btGravarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("teste criar obj", "Clicou botão grava ");

                try {
                    criarObjPedido();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void criarObjPedido() throws ParseException {
        Log.i("teste criar obj", "Abriu metodo criar obj");
        String nomeClienteString = String.valueOf(nomeCliente.getText());
        String enderecoString = String.valueOf(enderecoCliente.getText());
        if(idUsuarioLogado.equals(0) || nomeClienteString.isEmpty() || enderecoString.isEmpty()
                || totalItens.equals(BigDecimal.ZERO) || totalQuantidade.equals(BigDecimal.ZERO)
                || valorTotalPedido.equals(BigDecimal.ZERO)) // comparar valores com bigDecimal usar o compareto
        {
            Toast.makeText(this, "Todos os campos precisam ser preenchidos@", Toast.LENGTH_SHORT).show();

        }else{
            Pedido novoPedido = new Pedido(idUsuarioLogadoLong, nomeClienteString,
                    enderecoString, data2, totalItens, totalQuantidade, valorTotalPedido);

            pedidoDAO.inserirPedido(novoPedido);
            startActivity(new Intent(NovoPedido.this, ListaPedidosActivity.class));
        }


    }


    @Override
    protected void onResume() {
        super.onResume();

    }



    public void exibirProdutosCarrinho(){
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

    public void clicouBotaoAddProduto(){
        btAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogEditarProdutoPedido().show(getSupportFragmentManager(),"teste");

//                if(getIntent().getSerializableExtra("flag").equals(true)){
//                    pedidoItemList.add((PedidoItem) getIntent().getSerializableExtra("obj"));
//                    exibirProdutos();
//                }

            }
        });
    }
    private void calculoValoresResumo() {
        for(PedidoItem ped : pedidoItemList){
            totalQuantidade = totalQuantidade.add(BigDecimal.valueOf(1));
            totalItens = totalItens.add(BigDecimal.valueOf(ped.getQuantidade()));
            valorTotalPedido = valorTotalPedido.add(ped.getPrecoVenda().multiply(new BigDecimal(String.valueOf(ped.getQuantidade()))));
        }
        exibirValoresResumo();

    }

    private void exibirValoresResumo() {
        valorTotalProdutos.setText(String.valueOf(totalQuantidade));
        valorTotalItens.setText(String.valueOf(totalItens));
        valorTotal.setText(String.valueOf(valorTotalPedido));
    }
}
