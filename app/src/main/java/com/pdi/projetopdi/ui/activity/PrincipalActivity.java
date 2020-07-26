package com.pdi.projetopdi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pdi.projetopdi.R;

public class PrincipalActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_principal);

        Button botaoDirecionaPedido = findViewById(R.id.btPedido);
        Button botaoDirecionaProduto = findViewById(R.id.btProduto);

        botaoDirecionaProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PrincipalActivity.this,"Clicou botão produto",Toast.LENGTH_SHORT);
                startActivity(new Intent(PrincipalActivity.this,ListaProdutosActivity.class));
            }
        });

        botaoDirecionaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PrincipalActivity.this,"Clicou botão pedido",Toast.LENGTH_SHORT);
                startActivity(new Intent(PrincipalActivity.this, ListaPedidosActivity.class));
            }
        });
    }
}
