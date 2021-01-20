package com.pdi.projetopdi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.fragments.DialogEditarProdutoPedido;
import com.pdi.projetopdi.logic.NovoPedidoLogic;
import com.pdi.projetopdi.model.PedidoItem;
import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.repository.ProdutoRepository;

import java.util.ArrayList;

public class PedidoItensAdapter extends RecyclerView.Adapter<PedidoItensViewHolder> {

    private Context context;
    private ArrayList<PedidoItem> pedidosItens;
    private NovoPedidoLogic novoPedidoViewModel;

    private ProdutoRepository produtoRepository;
    private Produto produto;

    public PedidoItensAdapter(Context context, ArrayList<PedidoItem> pedidosItens) {
        this.context = context;
        this.pedidosItens = pedidosItens;
        novoPedidoViewModel = new NovoPedidoLogic(context);
        produtoRepository = ProdutoRepository.getInstance(context);
    }

    @NonNull
    @Override
    public PedidoItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_itens_do_pedido,
                parent, false);
        return new PedidoItensViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PedidoItensViewHolder holder, final int position) {

        final PedidoItem pedidoItem = pedidosItens.get(position);

        produto = new Produto();
        produto = produtoRepository.buscaProdutoPorID(pedidoItem.getIdProduto());

        holder.descricao.setText(produto.getDescricao());
        holder.precoVenda.setText("R$" + pedidoItem.getPrecoVenda());
        holder.quantidade.setText(String.valueOf(pedidoItem.getQuantidade()));
        holder.desconto.setText("R$" + pedidoItem.getValorDesconto());
        holder.valorTotal.setText("R$" + (pedidoItem.getPrecoVenda().multiply(pedidoItem.getValorDesconto())));

        holder.btRemoveProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItemDaLista(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });

        holder.cardPedidoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity) (context);
                FragmentManager fm = activity.getSupportFragmentManager();
                new DialogEditarProdutoPedido(pedidoItem).show(fm, "Edtar Item");
            }
        });

        novoPedidoViewModel.calculoValoresResumo(pedidosItens);
    }

    private void removerItemDaLista(int posicao) {
        pedidosItens.remove(posicao);
        notifyItemRemoved(posicao);
        notifyItemRangeChanged(posicao, pedidosItens.size());
        novoPedidoViewModel.calculoValoresResumo(pedidosItens);
    }

    @Override
    public int getItemCount() {
        return pedidosItens.size();
    }

}
