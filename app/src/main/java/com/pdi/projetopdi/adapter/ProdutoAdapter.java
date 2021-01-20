package com.pdi.projetopdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.ui.activity.EditarProdutoActivity;

import java.util.ArrayList;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoViewHolder> {

    private Context context;
    private ArrayList<Produto> itens;

    public ProdutoAdapter(Context context, ArrayList<Produto> itens) {
        this.context = context;
        this.itens = itens;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_lista_produtos,
                parent,false);

        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, final int position) {
        final Produto produto = itens.get(position);
        holder.descricao.setText(produto.getDescricao());
        holder.preco.setText(String.valueOf(produto.getPreco()));

        holder.btEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, EditarProdutoActivity.class);
                it.putExtra("id",produto.getIdproduto());
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
