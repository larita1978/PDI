package com.pdi.projetopdi.adapter;

import android.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;

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
