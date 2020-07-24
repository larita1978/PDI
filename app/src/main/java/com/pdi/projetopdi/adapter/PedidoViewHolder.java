package com.pdi.projetopdi.adapter;

import android.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;

public class PedidoViewHolder extends RecyclerView.ViewHolder {

    TextView cliente, valor;

    public PedidoViewHolder(@NonNull View itemView) {
        super(itemView);
        cliente = itemView.findViewById(R.id.pedidoCliente);
        valor = itemView.findViewById(R.id.pedidoValorTotal);
    }
}
