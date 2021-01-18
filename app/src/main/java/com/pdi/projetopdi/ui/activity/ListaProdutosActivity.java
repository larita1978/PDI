package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.ProdutoAdapter;
import com.pdi.projetopdi.repository.ProdutoRepository;
import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.ui.logic.ListaProdutosLogic;

import java.util.ArrayList;

public class ListaProdutosActivity extends AppCompatActivity {

    private EditText campoBuscar;
    private Button btBuscar;
    private RecyclerView recycler;
    private ProdutoAdapter adapter;
    private Button btNovoProduto;
    private ArrayList<Produto> itens;
    private ProdutoRepository pr;
    private ListaProdutosLogic mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        setTitle("Produtos");

        campoBuscar = findViewById(R.id.campoBuscar);
        btBuscar = findViewById(R.id.btBuscar);
        recycler = findViewById(R.id.recycler);
        btNovoProduto = findViewById(R.id.btNovoProduto);

        mp = new ViewModelProvider(this).get(ListaProdutosLogic.class);
//        pr = new ProdutoRepository(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                buscaritem();
//                exibirTodosProdutos();
//                exibirBtNovoProduto();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        exibirTodosProdutos();
    }
//
//    public void buscaritem(){
//        btBuscar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String buscarProduto = campoBuscar.getText().toString();
//                itens = pr.buscaProdutoDescricao(buscarProduto);
//
//                if(itens.isEmpty()){
//                    Toast.makeText(ListaProdutosActivity.this, "Item não encontrado",Toast.LENGTH_SHORT).show();
//                }else{
//                        exibirLista(itens);
//                }
//
//            }
//        });
//    }
//
//    public void exibirTodosProdutos(){
//        pr.inserirPrimeirosDadosProduto();
//        itens = pr.buscaProdutos();
//        exibirLista(itens);
//    }
//
//    public void exibirLista(ArrayList<Produto> produtos){
//        try{
//            if (!produtos.isEmpty()){
//                adapter = new ProdutoAdapter(ListaProdutosActivity.this, produtos);
//                GridLayoutManager layoutManager = new GridLayoutManager(this,2);
//
//                recycler.setLayoutManager(layoutManager);
//                recycler.setAdapter(adapter);
//            }
//        }catch (Exception e){
//            Toast.makeText(ListaProdutosActivity.this, "Ocorreu um erro inesperado", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void exibirBtNovoProduto(){
//        btNovoProduto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ListaProdutosActivity.this, NovoProdutoActivity.class));
//            }
//        });
//    }
}
