package com.pdi.projetopdi.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;

public class PedidoViewHolder extends RecyclerView.ViewHolder {

    protected TextView cliente, valor;
    protected  CardView cardPedido;

    public PedidoViewHolder(@NonNull View itemView) {
        super(itemView);
        cliente = itemView.findViewById(R.id.pedidoCliente);
        valor = itemView.findViewById(R.id.pedidoValorTotal);
        cardPedido = itemView.findViewById(R.id.cardviewPedido);
    }
}
