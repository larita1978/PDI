package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragmentCabecalho());
        list.add(new PageFragmentCarrinho());


        pager = findViewById(R.id.idViewPagerPedido);
        adapter = new ViewPagerPedidoAdapter(getSupportFragmentManager(),list);

        pager.setAdapter(adapter);
    }
}
