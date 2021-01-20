package com.pdi.projetopdi.logic;

import android.content.Context;

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

    private Pedido novoPedido;

    private PedidoRepository pedidoRepository;
    private PedidoItemRepository pedidoItemRepository;
    private Context context;

    public NovoPedidoLogic(Context context) {
        this.pedidoRepository = pedidoRepository.getInstance(context);
        this.context=context;
        pedidoItemRepository = new PedidoItemRepository(context);
    }

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

        ((NovoPedidoActivity)context).exibirValoresResumo(this.totalQuantidade,this.totalItens,this.valorTotalPedido);

    }
    public boolean validaCamposPreenchidos(int idUsuarioLogadoLong, String nomeClienteString,
                                           String enderecoString, String dataPedido, String totalItem, String totalQuant,
                                           String valorTotal, ArrayList<PedidoItem> pedidoItemList, int idPedido)
            throws ParseException {

        if((idUsuarioLogadoLong == 0)|| nomeClienteString.isEmpty() || enderecoString.isEmpty()
                || (totalItem =="0")
                || (totalQuant=="0")
                || (valorTotal =="0"))
        {
            return false;
        }

            criarObjPedido(idUsuarioLogadoLong,nomeClienteString, enderecoString,
                    dataPedido, totalItem,totalQuant, valorTotal, pedidoItemList, idPedido);

            return true;

    }


    public void criarObjPedido(int idUsuarioLogadoLong, String nomeClienteString,
                               String enderecoString, String dataPedido, String totalItens,
                               String totalQuantidade, String valorTotalPedido,
                               ArrayList<PedidoItem> pedidoItemList, int idPedido) throws ParseException {

        if(idPedido == 0) {
            novoPedido = new Pedido(idUsuarioLogadoLong, nomeClienteString,
                    enderecoString, dataPedido, new BigDecimal(totalItens), new BigDecimal(totalQuantidade),
                    new BigDecimal(valorTotalPedido));
            novoPedido.setIdPedido(pedidoRepository.inserirPedido(novoPedido));
        }else {
            novoPedido = new Pedido(idPedido,idUsuarioLogadoLong, nomeClienteString,
                    enderecoString, dataPedido, new BigDecimal(totalItens), new BigDecimal(totalQuantidade),
                    new BigDecimal(valorTotalPedido));
            pedidoRepository.atualizaPedido(novoPedido);
        }

        for (PedidoItem pedidoItem : pedidoItemList){
            pedidoItem.setIdPedido(novoPedido.getIdPedido());
            pedidoItemRepository.inserirPedidoItem(pedidoItem);
        }

    }

    public Pedido buscaPedido(int idPedido) {
        Pedido pedido = pedidoRepository.buscaPedidoPorID(idPedido);
        return pedido;
    }

    public ArrayList<PedidoItem> buscaItens(int idPedido) {
        ArrayList<PedidoItem> lista =pedidoItemRepository.buscaPedidoItemPorIDPedido(idPedido);
        return lista;
    }
}