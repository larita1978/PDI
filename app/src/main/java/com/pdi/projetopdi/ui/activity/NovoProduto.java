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

import java.util.ArrayList;

public class NovoProduto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

         final EditText novoPrdDescricao = findViewById(R.id.novoPrdDescricao);
         final EditText novoPrdPreco = findViewById(R.id.novoPrdPreco);
         Button novoPrdBotao = findViewById(R.id.novoPrdBotao);

        final ProdutoDAO pr = new ProdutoDAO(this);

        novoPrdBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double novoPrdPrecoD = Double.parseDouble(novoPrdPreco.getText().toString());
                String novoPrdDescricaoD = novoPrdDescricao.getText().toString();
                pr.setInserirProduto(new Produto(novoPrdDescricaoD,novoPrdPrecoD));
                Toast.makeText(NovoProduto.this,"Produto cadastrado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
