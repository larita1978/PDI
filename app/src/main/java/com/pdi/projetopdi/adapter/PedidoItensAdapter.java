package com.pdi.projetopdi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.modelo.PedidoItem;

import java.util.ArrayList;

public class PedidoItensAdapter extends RecyclerView.Adapter<PedidoItensViewHolder> {

    private Context context;
    private ArrayList<PedidoItem> pedidosItens;

    public PedidoItensAdapter(Context context, ArrayList<PedidoItem> pedidosItens) {
        this.context = context;
        this.pedidosItens = pedidosItens;
    }

    @NonNull
    @Override
    public PedidoItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_itens_add_pedido, parent, false);
        PedidoItensViewHolder viewHolder = new PedidoItensViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoItensViewHolder holder, int position) {
        final PedidoItem pedidoItem = pedidosItens.get(position);
        holder.precoVenda.setText("R$" + pedidoItem.getPrecoVenda());
        holder.quantidade.setText(String.valueOf(pedidoItem.getQuantidade()));
        holder.desconto.setText("R$" + pedidoItem.getValorDesconto());
        holder.valorTotal.setText("R$" + (pedidoItem.getPrecoVenda().multiply(pedidoItem.getValorDesconto())));
    }

    @Override
    public int getItemCount() {
        return pedidosItens.size();
    }
}
