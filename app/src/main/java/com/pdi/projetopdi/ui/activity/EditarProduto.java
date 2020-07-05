package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.dao.ProdutoDAO;
import com.pdi.projetopdi.modelo.Produto;

public class EditarProduto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        Long idProduto = (Long) getIntent().getSerializableExtra("id");

        Log.i("teste", String.valueOf(idProduto));
        //ArrayList<Produto> teste = extras.getStringArrayList();

        final EditText produtoDescricao = findViewById(R.id.produtoDescricao);
        final EditText produtoPreco = findViewById(R.id.produtoPreco);
        Button salvarPrdBotao = findViewById(R.id.salvarPrdBotao);

        final ProdutoDAO produtoDAO = new ProdutoDAO(this);
        final Produto prd = produtoDAO.buscaProdutoPorID(idProduto);

        produtoDescricao.setText(prd.getDescricao());
        produtoPreco.setText(String.format("%.2f",prd.getPreco()));

        salvarPrdBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Float novoPrdPrecoD = Float.parseP(String.valueOf(produtoPreco.getText()));
//                String novoPrdDescricaoD = produtoDescricao.getText().toString();
                produtoDAO.updateProduto(prd);
                Toast.makeText(EditarProduto.this,"Poduto cadastrado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
