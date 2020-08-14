package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.ViewPagerPedidoAdapter;
import com.pdi.projetopdi.dao.PedidoDAO;
import com.pdi.projetopdi.fragments.PageFragmentCabecalho;
import com.pdi.projetopdi.fragments.PageFragmentCarrinho;
import com.pdi.projetopdi.modelo.Pedido;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NovoPedidoActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter adapter;
    private List<Fragment> listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        exibirFragments();



//        try {
//            pedidodao.inserirPedido(pedido);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

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
