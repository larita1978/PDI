package com.pdi.projetopdi.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pdi.projetopdi.R;

public class PageFragmentPedidoCarrinho extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.page_itens, container, false);

        Button btAddProduto = rootView.findViewById(R.id.btAddProduto);

        btAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowDialog();
            }
        });
        return rootView;
    }

    public void createAndShowDialog() {
        Dialog dialog = new Dialog();
        dialog.setContentView(R.layout.dialog_add_editar_produto_pedido);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }
}
