package com.pdi.projetopdi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.ProdutoAdapter;
import com.pdi.projetopdi.dao.ProdutoDAO;
import com.pdi.projetopdi.modelo.Produto;

import java.util.ArrayList;

public class ListaProdutosActivity extends AppCompatActivity {

    private EditText campoBuscar;
    private Button btBuscar;
    private RecyclerView recycler;
    private ProdutoAdapter adapter;
    private Button btNovoProduto;
    private ArrayList<Produto> itens;
    private ProdutoDAO pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        setTitle("Produtos");

        campoBuscar = findViewById(R.id.campoBuscar);
        btBuscar = findViewById(R.id.btBuscar);
        recycler = findViewById(R.id.recycler);
        btNovoProduto = findViewById(R.id.btNovoProduto);

        pr = new ProdutoDAO(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buscaritem();
                exibirTodosProdutos();
                exibirBtNovoProduto();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        exibirTodosProdutos();
    }

    public void buscaritem(){
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buscarProduto = campoBuscar.getText().toString();
                itens = pr.buscaProdutoDescricao(buscarProduto);

                if(itens.isEmpty()){
                    Toast.makeText(ListaProdutosActivity.this, "Item não encontrado",Toast.LENGTH_SHORT).show();
                }else{
                        exibirLista(itens);
                }

            }
        });
    }

    public void exibirTodosProdutos(){
        pr.inserirPrimeirosDados();
        itens = pr.buscaProdutos();
        exibirLista(itens);
    }

    public void exibirLista(ArrayList<Produto> produtos){
        try{
            if (!produtos.isEmpty()){
                adapter = new ProdutoAdapter(ListaProdutosActivity.this, produtos);
                GridLayoutManager layoutManager = new GridLayoutManager(this,2);

                recycler.setLayoutManager(layoutManager);
                recycler.setAdapter(adapter);
            }
        }catch (Exception e){
            Toast.makeText(ListaProdutosActivity.this, "Ocorreu um erro inesperado", Toast.LENGTH_SHORT).show();
        }
    }

    private void exibirBtNovoProduto(){
        btNovoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaProdutosActivity.this, NovoProduto.class));
            }
        });
    }
}
