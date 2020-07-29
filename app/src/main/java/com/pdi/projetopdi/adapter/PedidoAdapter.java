package com.pdi.projetopdi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.modelo.Pedido;

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
            View view = LayoutInflater.from(context).inflate(R.layout.pedidos, parent, false);
            PedidoViewHolder viewHolder = new PedidoViewHolder(view);
            return viewHolder;
        }

        //aqui preenche os dados do layout
        @Override
        public void onBindViewHolder(@NonNull PedidoViewHolder holder, final int position) {
            final Pedido pedido = pedidos.get(position);
            holder.cliente.setText(pedido.getCliente());
            holder.valor.setText("R$ "+String.valueOf(pedido.getValorTotal()));

            holder.cardPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Acessou","pós validação");
//                    Intent it = new Intent(context, EditarProdutoActivity.class);
//                    it.putExtra("id", produto.getIdproduto());
//                    context.startActivity(it);
                }
            });
        }

        @Override
        public int getItemCount() {
            return pedidos.size();
        }
}