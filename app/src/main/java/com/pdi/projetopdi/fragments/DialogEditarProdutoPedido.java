package com.pdi.projetopdi.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.dao.ProdutoDAO;
import com.pdi.projetopdi.modelo.Produto;

import java.math.BigDecimal;

public class DialogEditarProdutoPedido extends DialogFragment {

    private EditText produtoDigitadoEditText;
    private Button btBuscarProdutoDescricao;
    private TextView precoOriginalBanco;

    private EditText quantidadeProdutoDigitadoEditText;
    private  EditText precoVendaDigitadoEditText;
    private  EditText descontoProdutoDigitadoEditText;
    private  TextView totalProdutoEditText;
    private Produto produtoObj;

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

        produtoDigitadoEditText = view.findViewById(R.id.digitarProduto);
        btBuscarProdutoDescricao = view.findViewById(R.id.btBuscarDescricao);
        precoOriginalBanco = view.findViewById(R.id.idPrecoProduto);
        quantidadeProdutoDigitadoEditText = view.findViewById(R.id.idQuantidade);
        precoVendaDigitadoEditText = view.findViewById(R.id.idPrecoVenda);
        descontoProdutoDigitadoEditText = view.findViewById(R.id.idDescontoProduto);
        totalProdutoEditText =view.findViewById(R.id.idValorTotalProduto);

        final ProdutoDAO prdDao = new ProdutoDAO(getActivity());


        btBuscarProdutoDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produtoObj = prdDao.buscaProdutoDesc(produtoDigitadoEditText.getText().toString());
                produtoDigitadoEditText.setText(String.valueOf(produtoObj.getDescricao()));
                precoOriginalBanco.setText(String.valueOf(produtoObj.getPreco()));
                totalProdutoEditText.setText(String.valueOf(produtoObj.getPreco()));
            }
        });


        View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

//                    if(precoVendaBigDecimal!=null) {
                        precoVendaBigDecimal = new BigDecimal(precoVendaDigitadoEditText.getText().toString());
                        precoBigDecimal = new BigDecimal(precoOriginalBanco.getText().toString());
                        totalProdutoEditText.setText(String.valueOf(precoBigDecimal.subtract(precoVendaBigDecimal)));
                    Log.i("teste","fora no foco");


                }

            }
        };

        precoVendaDigitadoEditText.setOnFocusChangeListener(focusListener);


        return view;
    }


}
