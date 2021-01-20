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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.PedidoItensAdapter;
import com.pdi.projetopdi.model.FormatDate;
import com.pdi.projetopdi.fragments.DialogEditarProdutoPedido;
import com.pdi.projetopdi.model.Pedido;
import com.pdi.projetopdi.model.PedidoItem;
import com.pdi.projetopdi.logic.NovoPedidoLogic;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

public class NovoPedidoActivity extends AppCompatActivity {

    private PedidoItensAdapter adapter;
    private RecyclerView recycler;
    public ArrayList<PedidoItem> pedidoItemList;
    private PedidoItem pedidoItem;

    private TextView dataPedido;
    private EditText nomeCliente;
    private EditText enderecoCliente;

    private TextView totalProdutos;
    private TextView totalItens;
    private TextView valorTotal;

    private Button btGravarPedido;

    private Button btAddProduto;
    private Button btExibirCarrinho;
    private LinearLayout linearteste;
    private Boolean show;

    private String data2;

    int idPedido;

//    private BigDecimal totalQuantidade;
//    private BigDecimal totalItens;
//    private BigDecimal valorTotalPedido;

    private SharedPreferences idUsuarioLogado;
    private int idUsuarioLogadoLong;

    private Pedido pedido;
//    private PedidoRepository pedidoRepository;

    private NovoPedidoLogic pedidoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);
        idPedido = 0;

        btExibirCarrinho = findViewById(R.id.btExibirCarrinho);
        linearteste = findViewById(R.id.idLinearTeste);

        dataPedido = findViewById(R.id.dataPedido);

        nomeCliente = findViewById(R.id.idNomeCliente);
        enderecoCliente = findViewById(R.id.idEnderecoCliente);

        btAddProduto = findViewById(R.id.btAddProduto);
        recycler = findViewById(R.id.recyclerProdutosCabecalho);

        //campos resumo
        totalProdutos = findViewById(R.id.idTextResumoTotalProdutos);
        totalItens = findViewById(R.id.idTextResumoTotalItens);
        valorTotal = findViewById(R.id.idTextResumoValorTotal);

        btGravarPedido = findViewById(R.id.btGravarPedido);


    }

    @Override
    protected void onStart() {
        super.onStart();

        //aqui pega o id do user logado
        idUsuarioLogado = PreferenceManager.getDefaultSharedPreferences(this);
        idUsuarioLogadoLong = idUsuarioLogado.getInt("login", 0);
        Log.i("idLogin", String.valueOf(idUsuarioLogadoLong));

        pedidoItemList = new ArrayList<>();
        //variavel controle se irá aparecer os produtos ou não
        show = true;

        if (getIntent().getSerializableExtra("id") != null)
            idPedido = (int) getIntent().getSerializableExtra("id");

        pedidoViewModel = new NovoPedidoLogic(this);

        if (!(idPedido == 0)) {
            Log.i("oi", "veio id");
            pedido = pedidoViewModel.buscaPedido(idPedido);
            pedidoItemList = pedidoViewModel.buscaItens(idPedido);

            try {
                nomeCliente.setText(pedido.getCliente());
                enderecoCliente.setText(pedido.getEndereco());
                dataPedido.setText(pedido.getDataPedido());
                totalProdutos.setText(String.valueOf(pedido.getTotalProdutos()));
                totalItens.setText(String.valueOf(pedido.getTotalItens()));
                valorTotal.setText(String.valueOf(pedido.getValorTotal()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            try {
                exibeData();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

//        pedidoRepository = PedidoRepository.getInstance(this);
        pedido = new Pedido();

//        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));
//        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));
//        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));

    }

    private void exibeData() throws ParseException {
        //declaração de datas
        long date1 = System.currentTimeMillis();
        FormatDate format = new FormatDate();
        format.setDataLong(date1);
        data2 = format.getDataLong();
        dataPedido.setText(data2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        exibirProdutos();
        clicouBotaoAddProduto();
        exibirProdutosCarrinho();
        clicouBotaoGravarPedido();
    }

    public void exibirProdutosCarrinho() {
        btExibirCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show) {
                    linearteste.setVisibility(View.GONE);
                    show = false;
                } else {
                    linearteste.setVisibility(View.VISIBLE);
                    show = true;
                }
            }
        });
    }

    public void exibirProdutos() {
        adapter = new PedidoItensAdapter(this, pedidoItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    public void clicouBotaoAddProduto() {
        btAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        new DialogEditarProdutoPedido(pedidoItem).show(getSupportFragmentManager(), "Novo Pedido");
                    }
                });

//                if(getIntent().getSerializableExtra("flag").equals(true)){
//                    pedidoItemList.add((PedidoItem) getIntent().getSerializableExtra("obj"));
//                    exibirProdutos();
//                }

            }
        });
    }


    public void exibirValoresResumo(BigDecimal totalQuantidade, BigDecimal totalItens, BigDecimal valorTotalPedido) {
        totalProdutos.setText(String.valueOf(totalQuantidade));
        this.totalItens.setText(String.valueOf(totalItens));
        valorTotal.setText(String.valueOf(valorTotalPedido));
    }

    private void clicouBotaoGravarPedido() {
        btGravarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("teste criar obj", "Clicou botão grava ");

                try {
                    String nomeClienteString = String.valueOf(nomeCliente.getText());
                    String enderecoString = String.valueOf(enderecoCliente.getText());
                    pedidoViewModel.validaCamposPreenchidos(1,
                            nomeClienteString, enderecoString, data2,
                            totalItens.getText().toString(),
                            totalProdutos.getText().toString(),
                            valorTotal.getText().toString(), pedidoItemList);
                    startActivity(new Intent(NovoPedidoActivity.this, ListaPedidosActivity.class));


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
