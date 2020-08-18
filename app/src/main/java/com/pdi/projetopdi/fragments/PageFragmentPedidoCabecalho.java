package com.pdi.projetopdi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.dao.PedidoDAO;
import com.pdi.projetopdi.modelo.Pedido;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageFragmentPedidoCabecalho extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.page_cabecalho, container, false);

        long date1 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");


        TextView dataPedido = rootView.findViewById(R.id.dataPedido);
        EditText nomeCliente = rootView.findViewById(R.id.idNomeCliente);
        EditText enderecoCliente = rootView.findViewById(R.id.idEnderecoCliente);

//        PedidoDAO pedidodao = new PedidoDAO(this);
        Pedido pedido = new Pedido();

//        pedido.setCliente(nomeCliente.getText().toString());
//        pedido.setEndereco(enderecoCliente.getText().toString());
        String data2 = sdf.format(date1);
//        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        dataPedido.setText(data2);
//        nomeCliente.setText("LArissa");
        return rootView;
    }



}
