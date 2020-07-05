package com.pdi.projetopdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.modelo.Produto;
import com.pdi.projetopdi.ui.activity.EditarProduto;
import com.pdi.projetopdi.ui.activity.ListaProdutosActivity;
import com.pdi.projetopdi.ui.activity.NovoProduto;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

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
        View view = LayoutInflater.from(context).inflate(R.layout.produtos, parent,false);
        ProdutoViewHolder viewHolder = new ProdutoViewHolder(view);
        return viewHolder;
    }
    //aqui preenche os dados do layout
    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, final int position) {
        final Produto produto = itens.get(position);
        holder.descricao.setText(produto.getDescricao());
        holder.preco.setText(String.format("%.2f",produto.getPreco()));

        holder.btEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, EditarProduto.class);
                it.putExtra("id",produto.getIdproduto());  // verificar para passar somente o id mais fácil (pesquisar)
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
