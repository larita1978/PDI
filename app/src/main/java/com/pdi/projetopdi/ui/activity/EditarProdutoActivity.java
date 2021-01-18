package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.repository.ProdutoRepository;
import com.pdi.projetopdi.model.Produto;

import java.math.BigDecimal;

public class EditarProdutoActivity extends AppCompatActivity {

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

        final ProdutoRepository produtoDAO = new ProdutoRepository(this);
        final Produto prd = produtoDAO.buscaProdutoPorID(idProduto);

        produtoDescricao.setText(prd.getDescricao());
        produtoPreco.setText(String.valueOf(prd.getPreco()));

        salvarPrdBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigDecimal novoPrdPrecoD = new BigDecimal(produtoPreco.getText().toString());
                String novoPrdDescricaoD = produtoDescricao.getText().toString();
                produtoDAO.updateProduto(new Produto(Math.toIntExact(prd.getIdproduto()),novoPrdDescricaoD,novoPrdPrecoD));
                Toast.makeText(EditarProdutoActivity.this,"Poduto alterado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
