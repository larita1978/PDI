package com.pdi.projetopdi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.model.PedidoItem;
import com.pdi.projetopdi.ui.logic.NovoPedidoLogic;

import java.util.ArrayList;

public class PedidoItensAdapter extends RecyclerView.Adapter<PedidoItensViewHolder> implements MetodosReciclerView{

    private Context context;
    private ArrayList<PedidoItem> pedidosItens;
    private MetodosReciclerView clicouItem;
    private NovoPedidoLogic novoPedidoViewModel;

    public PedidoItensAdapter(Context context, ArrayList<PedidoItem> pedidosItens) {
        this.context = context;
        this.pedidosItens = pedidosItens;
        novoPedidoViewModel = new NovoPedidoLogic(context);
    }

    @NonNull
    @Override
    public PedidoItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_itens_do_pedido,
                parent, false);
        PedidoItensViewHolder viewHolder = new PedidoItensViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PedidoItensViewHolder holder, final int position) {
        final PedidoItem pedidoItem = pedidosItens.get(position);
//        holder.descricao.setText(pedidoItem.getIdPedidoItem());
        holder.precoVenda.setText("R$" + pedidoItem.getPrecoVenda());
        holder.quantidade.setText(String.valueOf(pedidoItem.getQuantidade()));
        holder.desconto.setText("R$" + pedidoItem.getValorDesconto());
        holder.valorTotal.setText("R$" + (pedidoItem.getPrecoVenda().multiply(pedidoItem.getValorDesconto())));

        holder.btRemoveProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               removerItemDaLista(holder,position);
               notifyItemRemoved(position);
               notifyItemRangeChanged(position, getItemCount());
            }
        });

        holder.cardPedidoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, DialogEditarProdutoPedido.class));
//                new DialogEditarProdutoPedido(pedidoItem).onStart();
//                new DialogEditarProdutoPedido(pedidoItem).show(((NovoPedidoActivity)mContext).getSupportFragmentManager());
            }
        });

        novoPedidoViewModel.calculoValoresResumo(pedidosItens);
    }

    private void removerItemDaLista(PedidoItensViewHolder holder,int posicao){
//        posicao = holder.getAdapterPosition();
        pedidosItens.remove(posicao);
        notifyItemRemoved(posicao);
        notifyItemRangeChanged(posicao,pedidosItens.size());
        novoPedidoViewModel.calculoValoresResumo(pedidosItens);
    }

    public void add(PedidoItem itemNovo,int posicao) {
        pedidosItens.add(itemNovo);
        notifyItemInserted(posicao);
    }


    @Override
    public int getItemCount() {
        return pedidosItens.size();
    }

    @Override
    public void editaItem() {

    }
}
