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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.PedidoItensAdapter;
import com.pdi.projetopdi.fragments.DialogEditarProdutoPedido;
import com.pdi.projetopdi.logic.NovoPedidoLogic;
import com.pdi.projetopdi.model.FormatDate;
import com.pdi.projetopdi.model.Pedido;
import com.pdi.projetopdi.model.PedidoItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;

public class NovoPedidoActivity extends AppCompatActivity {

    private PedidoItensAdapter adapter;
    private RecyclerView recycler;
    public ArrayList<PedidoItem> listaPedidoItens;

    private TextView dataPedidoTextView;
    private EditText nomeClienteEditText;
    private EditText enderecoClienteEditText;

    private TextView totalProdutosTextView;
    private TextView totalItensTextView;
    private TextView valorTotalTextView;

    private Button gravarPedidoButton;

    private Button addProdutoButton;
    private Button exibirCarrinhoButton;
    private LinearLayout linearteste;
    private Boolean showProdutosPedido;

    private String dataAtual;

    int idPedido;

    private SharedPreferences idUsuarioLogado;
    private int idUsuarioLogadoLong;

    private Pedido pedido;

    private NovoPedidoLogic pedidoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);
        idPedido = 0;

        exibirCarrinhoButton = findViewById(R.id.btExibirCarrinho);
        linearteste = findViewById(R.id.idLinearTeste);

        dataPedidoTextView = findViewById(R.id.dataPedido);

        nomeClienteEditText = findViewById(R.id.idNomeCliente);
        enderecoClienteEditText = findViewById(R.id.idEnderecoCliente);

        addProdutoButton = findViewById(R.id.btAddProduto);
        recycler = findViewById(R.id.recyclerProdutosCabecalho);

        //campos resumo
        totalProdutosTextView = findViewById(R.id.idTextResumoTotalProdutos);
        totalItensTextView = findViewById(R.id.idTextResumoTotalItens);
        valorTotalTextView = findViewById(R.id.idTextResumoValorTotal);

        gravarPedidoButton = findViewById(R.id.btGravarPedido);


    }

    @Override
    protected void onStart(){
        super.onStart();

        //aqui pega o id do user logado
        idUsuarioLogado = PreferenceManager.getDefaultSharedPreferences(this);
        idUsuarioLogadoLong = idUsuarioLogado.getInt("login", 0);
        Log.i("idLogin", String.valueOf(idUsuarioLogadoLong));

        listaPedidoItens = new ArrayList<>();

        //variavel controle se os produtos estarão visíveis ou não
        showProdutosPedido = true;

        if (getIntent().getSerializableExtra("id") != null)
            idPedido = (int) getIntent().getSerializableExtra("id");

        pedidoViewModel = new NovoPedidoLogic(this);

        if (!(idPedido == 0)) {
            pedido = pedidoViewModel.buscaPedido(idPedido);
            listaPedidoItens = pedidoViewModel.buscaItens(idPedido);

            try {
                exibeInformacoesPedido();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            pedido = new Pedido();
            try {
                exibeData();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    private void exibeInformacoesPedido() throws ParseException {
        nomeClienteEditText.setText(pedido.getCliente());
        enderecoClienteEditText.setText(pedido.getEndereco());
        dataPedidoTextView.setText(pedido.getDataPedido());
        dataAtual=pedido.getDataPedido();
        totalProdutosTextView.setText(String.valueOf(pedido.getTotalProdutos()));
        totalItensTextView.setText(String.valueOf(pedido.getTotalItens()));
        valorTotalTextView.setText(String.valueOf(pedido.getValorTotal()));
    }

    private void exibeData() throws ParseException {

        FormatDate format = new FormatDate();
        format.setDataLong(System.currentTimeMillis());
        dataAtual = format.getDataLong();
        dataPedidoTextView.setText(dataAtual);
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
        exibirCarrinhoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showProdutosPedido) {
                    linearteste.setVisibility(View.GONE);
                    showProdutosPedido = false;
                } else {
                    linearteste.setVisibility(View.VISIBLE);
                    showProdutosPedido = true;
                }
            }
        });
    }

    public void exibirProdutos() {
        adapter = new PedidoItensAdapter(this, listaPedidoItens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    public void clicouBotaoAddProduto() {
        addProdutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new DialogEditarProdutoPedido().show(getSupportFragmentManager(), "Novo Pedido");
                    }
                });
            }
        });
    }


    public void exibirValoresResumo(BigDecimal totalQuantidade, BigDecimal totalItens, BigDecimal valorTotalPedido) {
        totalProdutosTextView.setText(String.valueOf(totalQuantidade));
        totalItensTextView.setText(String.valueOf(totalItens));
        valorTotalTextView.setText(String.valueOf(valorTotalPedido.setScale(2, RoundingMode.HALF_EVEN)));
    }

    private void clicouBotaoGravarPedido() {
        gravarPedidoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nomeClienteString = nomeClienteEditText.getText().toString();
                    String enderecoString = enderecoClienteEditText.getText().toString();

                    boolean verificanull = pedidoViewModel.validaCamposPreenchidos(idUsuarioLogadoLong,
                            nomeClienteString, enderecoString, dataAtual,
                            totalItensTextView.getText().toString(),
                            totalProdutosTextView.getText().toString(),
                            valorTotalTextView.getText().toString(), listaPedidoItens,idPedido);

                    if (!verificanull) {
                        Toast.makeText(NovoPedidoActivity.this,
                                "Verifique se as informações inseridas", Toast.LENGTH_SHORT).show();
                    } else {

                        startActivity(new Intent(NovoPedidoActivity.this, ListaPedidosActivity.class));
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}