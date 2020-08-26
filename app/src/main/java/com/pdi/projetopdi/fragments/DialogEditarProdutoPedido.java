package com.pdi.projetopdi.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.dao.ProdutoDAO;
import com.pdi.projetopdi.modelo.Produto;
import com.pdi.projetopdi.ui.activity.NovoPedidoActivity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DialogEditarProdutoPedido extends DialogFragment {

    private EditText produtoDigitado;
    private Button buscarProdutoDescricao;
    private TextView preco;

    private EditText quantidadeProdutoDigitado;
    private  EditText precoVendaDigitado;
    private  EditText descontoProdutoDigitado;
    private  EditText totalProduto;
    private Produto prd ;

    private BigDecimal precoVendaBigDecimal;
    private BigDecimal precoDescontoBigDecimal;
    private BigDecimal precoBigDecimal;

    static DialogEditarProdutoPedido newInstance(){
        return new DialogEditarProdutoPedido();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog);
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_editar_produto_pedido, container, false);

        produtoDigitado = view.findViewById(R.id.digitarProduto);
        buscarProdutoDescricao = view.findViewById(R.id.btBuscarDescricao);
        preco = view.findViewById(R.id.idPrecoProduto);
        quantidadeProdutoDigitado = view.findViewById(R.id.idQuantidade);
        precoVendaDigitado = view.findViewById(R.id.idPrecoVenda);
        descontoProdutoDigitado = view.findViewById(R.id.idDescontoProduto);
//        totalProduto =view.findViewById(R.id.idValorTotalProduto);

        final ProdutoDAO prdDao = new ProdutoDAO(getActivity());


        buscarProdutoDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prd = prdDao.buscaProdutoDesc(produtoDigitado.getText().toString());
                produtoDigitado.setText(String.valueOf(prd.getDescricao()));
                preco.setText(String.valueOf(prd.getPreco()));
//                totalProduto.setText(String.valueOf(prd.getPreco()));
            }
        });

        if(precoVendaBigDecimal!=null) {
            precoVendaBigDecimal = new BigDecimal(precoVendaDigitado.getText().toString());
            totalProduto.setText(String.valueOf(precoBigDecimal.subtract(precoVendaBigDecimal)));
        }

        return view;
    }


}
