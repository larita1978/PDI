package com.pdi.projetopdi.ui.logic;

import android.content.Context;
import android.util.Log;

import com.pdi.projetopdi.model.Pedido;
import com.pdi.projetopdi.model.PedidoItem;
import com.pdi.projetopdi.repository.PedidoRepository;
import com.pdi.projetopdi.ui.activity.NovoPedidoActivity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

public class NovoPedidoLogic {

    private BigDecimal totalQuantidade;
    private BigDecimal totalItens;
    private BigDecimal valorTotalPedido;

    private PedidoRepository pedidoRepository;
    private Context context;

    public NovoPedidoLogic(Context context) {
        this.pedidoRepository = pedidoRepository.getInstance(context);
        this.context=context;
    }
    public BigDecimal getTotalQuantidade() {
        return totalQuantidade;
    }

    public void setTotalQuantidade(BigDecimal totalQuantidade) {
        this.totalQuantidade = totalQuantidade;
    }

    public BigDecimal getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(BigDecimal totalItens) {
        this.totalItens = totalItens;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    public void calculoValoresResumo(ArrayList<PedidoItem> pedidoItemList) {
        totalQuantidade = BigDecimal.ZERO;
        totalItens =BigDecimal.ZERO;
        valorTotalPedido = BigDecimal.ZERO;
        for(PedidoItem ped : pedidoItemList){
            totalQuantidade = totalQuantidade.add(BigDecimal.valueOf(1));
            this.totalItens = totalItens.add(BigDecimal.valueOf(ped.getQuantidade()));
            this.valorTotalPedido = valorTotalPedido.add(ped.getPrecoVenda().multiply(new BigDecimal(String.valueOf(ped.getQuantidade()))));
        }
        ((NovoPedidoActivity)context).exibirValoresResumo(totalQuantidade,totalItens,valorTotalPedido);

    }
    public void validaCamposPreenchidos(int idUsuarioLogadoLong, String nomeClienteString,
                                               String enderecoString, String data2)
            throws ParseException {
        Log.i("teste criar obj", "Abriu metodo criar obj");

        if((idUsuarioLogadoLong == 0)|| nomeClienteString.isEmpty() || enderecoString.isEmpty()
                ||(totalItens.compareTo(BigDecimal.ZERO) ==0)
                || (totalQuantidade.compareTo(BigDecimal.ZERO)==0)
                || (valorTotalPedido.compareTo(BigDecimal.ZERO) ==0)) // comparar valores com bigDecimal usar o compareto
        {
            Log.i("TAG", "validaCamposPreenchidos: erro");

        }else{
            criarObjPedido(idUsuarioLogadoLong,nomeClienteString, enderecoString,
                    data2);
//            startActivity(new Intent(NovoPedidoActivity.this, ListaPedidosActivity.class));
        }


    }


    public void criarObjPedido(int idUsuarioLogadoLong, String nomeClienteString,
                                      String enderecoString, String data2) throws ParseException {
        Pedido novoPedido = new Pedido(idUsuarioLogadoLong, nomeClienteString,
                enderecoString, data2, totalItens, totalQuantidade, valorTotalPedido);

        this.pedidoRepository.inserirPedido(novoPedido);
    }
}
