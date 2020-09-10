package com.pdi.projetopdi.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.adapter.ViewPagerPedidoAdapter;
import com.pdi.projetopdi.dao.ProdutoDAO;
import com.pdi.projetopdi.fragments.PageFragmentPedidoCabecalho;
import com.pdi.projetopdi.fragments.PageFragmentPedidoCarrinho;
import com.pdi.projetopdi.fragments.PageFragmentsPedidoResumo;
import com.pdi.projetopdi.modelo.PedidoItem;

import java.util.ArrayList;
import java.util.List;

public class NovoPedidoActivity extends AppCompatActivity implements PageFragmentPedidoCarrinho.EnviaDados/*PageFragmentPedidoCabecalho.PassarDados*/ {

    private ViewPager pager;
    private PagerAdapter adapter;
    private List<Fragment> listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        exibirFragments();

        ProdutoDAO prdDao = new ProdutoDAO();


//        try {
//            pedidodao.inserirPedido(pedido);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }

    public void exibirFragments(){
        listFragment = new ArrayList<>();
        listFragment.add(new PageFragmentPedidoCabecalho());
        listFragment.add(new PageFragmentPedidoCarrinho());
        listFragment.add(new PageFragmentsPedidoResumo());


        pager = findViewById(R.id.idViewPagerPedido);
        adapter = new ViewPagerPedidoAdapter(getSupportFragmentManager(),listFragment);
        pager.setAdapter(adapter);

    }



    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

        PageFragmentPedidoCarrinho pageCarrinho = (PageFragmentPedidoCarrinho) getSupportFragmentManager().findFragmentById(R.id.idViewPagerPedido);
        pageCarrinho.setEnviaDados(this);
    }

    @Override
    public void dados(ArrayList<PedidoItem> teste) {
        for(PedidoItem pedido : teste){
            Log.i("Passar dados",String.valueOf(pedido.getQuantidade()));
        }
    }
}
