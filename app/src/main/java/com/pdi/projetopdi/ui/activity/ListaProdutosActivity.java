package com.pdi.projetopdi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.ProdutoAdapter;
import com.pdi.projetopdi.logic.ListaProdutosLogic;
import com.pdi.projetopdi.model.Produto;

import java.util.ArrayList;

public class ListaProdutosActivity extends AppCompatActivity {

    private EditText buscarEditText;
    private Button buscarButton;
    private Button novoProdutoButton;

    private RecyclerView recycler;
    private ProdutoAdapter adapter;
    private ArrayList<Produto> listaProdutos;

    private ListaProdutosLogic listaProdutosLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        setTitle("Produtos");

    }

    @Override
    protected void onStart() {
        super.onStart();
        buscarEditText = findViewById(R.id.campoBuscar);
        buscarButton = findViewById(R.id.btBuscar);
        recycler = findViewById(R.id.recycler);
        novoProdutoButton = findViewById(R.id.btNovoProduto);

        listaProdutosLogic = new ListaProdutosLogic(this, recycler);

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
        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaProdutos = listaProdutosLogic.buscarProdutoDigtado(buscarEditText.getText().toString());

                if(listaProdutos.isEmpty()){
                    Toast.makeText(ListaProdutosActivity.this, "Item não encontrado", Toast.LENGTH_SHORT).show();
                }else{
                        exibirLista(listaProdutos);
                }

            }
        });
    }

    public void exibirTodosProdutos(){
        listaProdutos = listaProdutosLogic.buscaProdutos();
        exibirLista(listaProdutos);
    }

    public void exibirLista(ArrayList<Produto> produtos){
        try{
            if (!produtos.isEmpty()){
                listaProdutosLogic.exibirProdutos(produtos);
            }
        }catch (Exception e){
            Toast.makeText(ListaProdutosActivity.this, "Ocorreu um erro inesperado", Toast.LENGTH_SHORT).show();
        }
    }

    private void exibirBtNovoProduto(){
        novoProdutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaProdutosActivity.this, EditarProdutoActivity.class));
            }
        });
    }
}
