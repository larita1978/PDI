package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.logic.EditarProdutoLogic;
import com.pdi.projetopdi.model.Produto;

public class EditarProdutoActivity extends AppCompatActivity {

    private EditText produtoDescricaoEditText;
    private EditText produtoPrecoEditText;
    private Button salvarProdutoBotao;

    private Long idProdutoSelecionado;

    private EditarProdutoLogic editarProdutoLogic;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        idProdutoSelecionado = (Long) getIntent().getSerializableExtra("id");

        produtoDescricaoEditText = findViewById(R.id.produtoDescricao);
        produtoPrecoEditText = findViewById(R.id.produtoPreco);
        salvarProdutoBotao = findViewById(R.id.salvarPrdBotao);

    }

    @Override
    protected void onStart() {
        super.onStart();

        editarProdutoLogic = new EditarProdutoLogic(this);


        if (idProdutoSelecionado != null) {
            produto = editarProdutoLogic.buscaProdutoPorID(idProdutoSelecionado);
            produtoDescricaoEditText.setText(produto.getDescricao());
            produtoPrecoEditText.setText(String.valueOf(produto.getPreco()));
        }
        clicouBotaoSalvar();
    }

    private void clicouBotaoSalvar() {
        salvarProdutoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idProdutoSelecionado != null) {
                    editarProdutoLogic.salvarPedidoEditado(
                            produtoPrecoEditText.getText().toString(),
                            produtoDescricaoEditText.getText().toString());
                }else{
                    editarProdutoLogic.salvarNovoProduto(produtoPrecoEditText.getText().toString(),
                            produtoDescricaoEditText.getText().toString());
                }

                Toast.makeText(EditarProdutoActivity.this, "Poduto salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
