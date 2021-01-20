package com.pdi.projetopdi.logic;

import android.content.Context;
import android.util.Log;

import com.pdi.projetopdi.model.Pedido;
import com.pdi.projetopdi.model.PedidoItem;
import com.pdi.projetopdi.repository.PedidoItemRepository;
import com.pdi.projetopdi.repository.PedidoRepository;
import com.pdi.projetopdi.ui.activity.NovoPedidoActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;

public class NovoPedidoLogic {

     private BigDecimal totalQuantidade;
     private BigDecimal totalItens;
     private BigDecimal valorTotalPedido;

    private PedidoRepository pedidoRepository;
    private PedidoItemRepository pedidoItemRepository;
    private Context context;

    public NovoPedidoLogic(Context context) {
        this.pedidoRepository = pedidoRepository.getInstance(context);
        this.context=context;
        pedidoItemRepository = new PedidoItemRepository(context);
    }
//    public BigDecimal getTotalQuantidade() {
//        return totalQuantidade;
//    }
//
//    public void setTotalQuantidade(BigDecimal totalQuantidade) {
//        this.totalQuantidade = totalQuantidade;
//    }
//
//    public BigDecimal getTotalItens() {
//        return totalItens;
//    }
//
//    public void setTotalItens(BigDecimal totalItens) {
//        this.totalItens = totalItens;
//    }
//
//    public BigDecimal getValorTotalPedido() {
//        return valorTotalPedido;
//    }
//
//    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
//        this.valorTotalPedido = valorTotalPedido;
//    }

    public void calculoValoresResumo(ArrayList<PedidoItem> pedidoItemList) {

        totalQuantidade = BigDecimal.ZERO;
        totalItens =BigDecimal.ZERO;
        valorTotalPedido = BigDecimal.ZERO;

        for(PedidoItem ped : pedidoItemList){
            this.totalQuantidade =totalQuantidade.add(BigDecimal.valueOf(1));
            this.totalItens = totalItens.add(BigDecimal.valueOf(ped.getQuantidade()));
            this.valorTotalPedido =valorTotalPedido.add(ped.getPrecoVenda().multiply(
                    new BigDecimal(String.valueOf(ped.getQuantidade()))).setScale(2, RoundingMode.HALF_EVEN));
        }
//        setTotalQuantidade(totalQuantidade);
//        setTotalItens(totalItens);
//        setValorTotalPedido(valorTotalPedido);

        ((NovoPedidoActivity)context).exibirValoresResumo(this.totalQuantidade,this.totalItens,this.valorTotalPedido);

    }
    public void validaCamposPreenchidos(int idUsuarioLogadoLong, String nomeClienteString,
                                               String enderecoString, String data2, String totalItem, String totalQuant,
                                        String valorTotal,ArrayList<PedidoItem> pedidoItemList)
            throws ParseException {
        Log.i("teste criar obj", "Abriu metodo criar obj");

        if((idUsuarioLogadoLong == 0)|| nomeClienteString.isEmpty() || enderecoString.isEmpty()
                || (totalItem =="0")
                || (totalQuant=="0")
                || (valorTotal =="0"))
        {
            Log.i("TAG", "validaCamposPreenchidos: erro");
        }else{
//            totalQuantidade = new BigDecimal(totalQuant);
//            totalItens = new BigDecimal(totalItem);
//            valorTotalPedido = new BigDecimal(valorTotal);
            criarObjPedido(idUsuarioLogadoLong,nomeClienteString, enderecoString,
                    data2, totalItem,totalQuant, valorTotal, pedidoItemList);
//            startActivity(new Intent(NovoPedidoActivity.this, ListaPedidosActivity.class));
        }


    }


    public void criarObjPedido(int idUsuarioLogadoLong, String nomeClienteString,
                                      String enderecoString, String data2, String totalItens,
                               String totalQuantidade, String valorTotalPedido, ArrayList<PedidoItem> pedidoItemList) throws ParseException {

        Pedido novoPedido = new Pedido(idUsuarioLogadoLong, nomeClienteString,
                enderecoString, data2, new BigDecimal(totalItens), new BigDecimal(totalQuantidade),
                new BigDecimal(valorTotalPedido));

        novoPedido.setIdPedido(pedidoRepository.inserirPedido(novoPedido));

        for (PedidoItem ped : pedidoItemList){
            ped.setIdPedido(novoPedido.getIdPedido());
            pedidoItemRepository.inserirPedidoItem(ped);
        }

    }

    public Pedido buscaPedido(int idPedido) {
        Pedido ped = pedidoRepository.buscaPedidoPorID(idPedido);
        return ped;
    }

    public ArrayList<PedidoItem> buscaItens(int idPedido) {
        ArrayList<PedidoItem> lista =pedidoItemRepository.buscaPedidoItemPorIDPedido(idPedido);
        return lista;
    }
}
