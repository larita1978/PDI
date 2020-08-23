package com.pdi.projetopdi.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.dao.ProdutoDAO;
import com.pdi.projetopdi.modelo.Produto;

import java.util.ArrayList;

public class DialogEditarProdutoPedido extends DialogFragment {

    private MultiAutoCompleteTextView buscaProduto;
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

        buscaProduto = view.findViewById(R.id.digitarProduto);

        ProdutoDAO prdDao = new ProdutoDAO();

        Produto[] buscaBanco = prdDao.buscaProdutoDescricao(buscaProduto.getText().toString());

        ArrayAdapter<Produto> buscar = new ArrayAdapter<Produto>(this,android.R.layout.simple_list_item_1 ,buscaBanco);
        Produto prd = new Produto();

        prd.setDescricao();
        return view;
    }
}
