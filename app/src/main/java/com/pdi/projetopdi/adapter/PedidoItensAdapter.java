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
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_itens_do_pedido, parent, false);
        PedidoItensViewHolder viewHolder = new PedidoItensViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PedidoItensViewHolder holder, final int position) {
        final PedidoItem pedidoItem = pedidosItens.get(position);
        holder.precoVenda.setText("R$" + pedidoItem.getPrecoVenda());
        holder.quantidade.setText(String.valueOf(pedidoItem.getQuantidade()));
        holder.desconto.setText("R$" + pedidoItem.getValorDesconto());
        holder.valorTotal.setText("R$" + (pedidoItem.getPrecoVenda().multiply(pedidoItem.getValorDesconto())));

        holder.btRemoveProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               removerItemDaLista(holder,position);
               notifyItemRemoved(position);
            }
        });
    }

    private void removerItemDaLista(PedidoItensViewHolder holder,int posicao){
        int novaPosition = holder.getAdapterPosition();
        pedidosItens.remove(novaPosition);
        notifyItemRemoved(novaPosition);
        notifyItemRangeChanged(novaPosition,pedidosItens.size());

    }

    @Override
    public int getItemCount() {
        return pedidosItens.size();
    }
}
