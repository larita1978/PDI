package com.pdi.projetopdi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.ProdutoAdapter;
import com.pdi.projetopdi.dao.DadosHelper;
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

        campoBuscar = findViewById(R.id.campoBuscar);
        btBuscar = findViewById(R.id.btBuscar);
        recycler = findViewById(R.id.recycler);
        btNovoProduto = findViewById(R.id.btNovoProduto);

        pr = new ProdutoDAO(this);

        buscaritem();
        exibirLista();
        exibirBtNovoProduto();



    }

    @Override
    protected void onResume() {
        super.onResume();
        exibirLista();
    }

    public void buscaritem(){
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teste = campoBuscar.getText().toString();
                itens = pr.buscaProdutoDescricao(teste);

                if(itens.isEmpty()){
                    Toast.makeText(ListaProdutosActivity.this, "Lista Vazia",Toast.LENGTH_SHORT).show();
                }else{
                        adapter = new ProdutoAdapter(ListaProdutosActivity.this, itens);
                        GridLayoutManager layoutManager = new GridLayoutManager(ListaProdutosActivity.this,2);

                        recycler.setLayoutManager(layoutManager);
                        recycler.setAdapter(adapter);

                }
            }
        });
    }

    public void exibirLista(){
        pr.inserirPrimeirosDados();
        itens = pr.buscaProdutos();

//        itens.add(new Produto(1,"Picolé",2.50));
//        itens.add(new Produto(2,"Picolé 2",2.30));
//        itens.add(new Produto(3,"Picolé 3",2.30));


        if (!itens.isEmpty()){

            adapter = new ProdutoAdapter(ListaProdutosActivity.this, itens);
            GridLayoutManager layoutManager = new GridLayoutManager(this,2);

            recycler.setLayoutManager(layoutManager);
            recycler.setAdapter(adapter);
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
