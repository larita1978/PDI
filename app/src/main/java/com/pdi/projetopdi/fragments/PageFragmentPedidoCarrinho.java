package com.pdi.projetopdi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.PedidoItensAdapter;
import com.pdi.projetopdi.modelo.PedidoItem;
import com.pdi.projetopdi.ui.activity.NovoPedidoActivity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PageFragmentPedidoCarrinho extends Fragment {

    private PedidoItensAdapter adapter;
    private RecyclerView recycler;
    private ArrayList<PedidoItem> pedidoItemList;
    private PedidoItem pedidoItem;

    EnviaDados callback;

    public void setEnviaDados(EnviaDados callback){
        this.callback = callback;
    }

    public interface EnviaDados{
        public void dados(ArrayList<PedidoItem> teste);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == getActivity().RESULT_OK && requestCode == 1) {
//            Bundle bundle = data.getExtras();
//            String teste = bundle.getString("teste");
//            Log.i("teste", teste);
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.page_itens, container, false);

        Button btAddProduto = rootView.findViewById(R.id.btAddProduto);
        recycler = rootView.findViewById(R.id.recyclerProdutosCabecalho);

        pedidoItemList = new ArrayList<PedidoItem>();
        pedidoItemList.add(new PedidoItem(5,5,new BigDecimal("69"),new BigDecimal("65"),new BigDecimal("4")));

        exibirProdutos();
        btAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = DialogEditarProdutoPedido.newInstance();
                dialog.show(getFragmentManager(),"tag");
            }
        });

        callback.dados(pedidoItemList);
//        Intent intent = new Intent(getActivity(), NovoPedidoActivity.class);
//        intent.putExtra("dados",pedidoItemList);
//        getActivity().startActivity(intent);
        return rootView;
    }

    public void exibirProdutos(){
        adapter = new PedidoItensAdapter(getContext(), pedidoItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

}
