package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.ViewPagerPedidoAdapter;
import com.pdi.projetopdi.fragments.PageFragmentCabecalho;
import com.pdi.projetopdi.fragments.PageFragmentCarrinho;

import java.util.ArrayList;
import java.util.List;

public class NovoPedidoActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter adapter;
    private List<Fragment> listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        TextView dataPedido = findViewById(R.id.dataPedido);

        exibirFragments();

//        dataPedido.setText(Date);

    }

    public void exibirFragments(){
        listFragment = new ArrayList<>();
        listFragment.add(new PageFragmentCabecalho());
        listFragment.add(new PageFragmentCarrinho());


        pager = findViewById(R.id.idViewPagerPedido);
        adapter = new ViewPagerPedidoAdapter(getSupportFragmentManager(),listFragment);
        pager.setAdapter(adapter);
    }
}
