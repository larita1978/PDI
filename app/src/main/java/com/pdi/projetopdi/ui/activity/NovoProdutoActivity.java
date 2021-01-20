package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.repository.ProdutoRepository;
import com.pdi.projetopdi.model.Produto;

import java.math.BigDecimal;

public class NovoProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

         final EditText novoPrdDescricao = findViewById(R.id.novoPrdDescricao);
         final EditText novoPrdPreco = findViewById(R.id.novoPrdPreco);
         Button novoPrdBotao = findViewById(R.id.novoPrdBotao);

        final ProdutoRepository pr =  ProdutoRepository.getInstance(this);

        novoPrdBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigDecimal novoPrdPrecoD = new BigDecimal(novoPrdPreco.getText().toString());
                String novoPrdDescricaoD = novoPrdDescricao.getText().toString();
                pr.inserirProduto(new Produto(novoPrdDescricaoD,novoPrdPrecoD));
                Toast.makeText(NovoProdutoActivity.this,"Produto cadastrado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
