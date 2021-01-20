package com.pdi.projetopdi.adapter.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;

public class PedidoItensViewHolder extends RecyclerView.ViewHolder {

    TextView descricao, precoVenda, quantidade, desconto, valorTotal;
    CardView cardPedidoItem;
    Button btRemoveProduto;

    public PedidoItensViewHolder(@NonNull View itemView) {
        super(itemView);

        descricao = itemView.findViewById(R.id.descricaoProdutoAdapter);
        precoVenda = itemView.findViewById(R.id.precoVendaAdapter);
        quantidade = itemView.findViewById(R.id.quantidadeAdapter);
        desconto = itemView.findViewById(R.id.descontoProdutoAdapter);
        valorTotal = itemView.findViewById(R.id.valorTotalProdutoAdapter);

        cardPedidoItem = itemView.findViewById(R.id.cardviewPedidoItem);

        btRemoveProduto = itemView.findViewById(R.id.btRemoveProduto);
    }
}
