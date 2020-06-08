package com.pdi.projetopdi.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.ui.activity.ListaProdutosActivity;
import com.pdi.projetopdi.ui.activity.NovoProduto;

import static androidx.core.content.ContextCompat.startActivity;

public class ProdutoViewHolder extends RecyclerView.ViewHolder {

    TextView descricao, preco;
    Button btEditar;

    public ProdutoViewHolder(@NonNull View itemView) {
        super(itemView);
        descricao = itemView.findViewById(R.id.descricaoProduto);
        preco = itemView.findViewById(R.id.precoProduto);
        btEditar = itemView.findViewById(R.id.btEditar);
    }
}
