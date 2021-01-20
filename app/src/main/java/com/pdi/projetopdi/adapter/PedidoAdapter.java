package com.pdi.projetopdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.viewHolder.PedidoViewHolder;
import com.pdi.projetopdi.model.Pedido;
import com.pdi.projetopdi.ui.activity.NovoPedidoActivity;

import java.util.ArrayList;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoViewHolder> {

        private Context context;
        private ArrayList<Pedido> pedidos;

        public PedidoAdapter(Context context, ArrayList<Pedido> pedidos) {
            this.context = context;
            this.pedidos = pedidos;
        }

        @NonNull
        @Override
        public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_lista_pedidos, parent, false);
            PedidoViewHolder viewHolder = new PedidoViewHolder(view);
            return viewHolder;
        }

        //aqui preenche os dados do layout
        @Override
        public void onBindViewHolder(@NonNull PedidoViewHolder holder, final int position) {
            final Pedido pedido = pedidos.get(position);
            holder.cliente.setText(pedido.getCliente());
            holder.valor.setText("R$ "+pedido.getValorTotal());

            holder.cardPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Acessou","pós validação");
                    Intent it = new Intent(context, NovoPedidoActivity.class);
                    it.putExtra("id", pedido.getIdPedido());
                    context.startActivity(it);
                }
            });
        }

        @Override
        public int getItemCount() {
            return pedidos.size();
        }
}