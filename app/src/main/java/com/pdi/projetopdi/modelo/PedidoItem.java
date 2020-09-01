package com.pdi.projetopdi.modelo;

import java.math.BigDecimal;

public class PedidoItem {
    private long idPedidoItem;
    private long idProduto;
    private long idPedido;
    private int quantidade;
    private BigDecimal precoOriginal;
    private BigDecimal precoVenda;
    private BigDecimal valorDesconto;

    public PedidoItem(long idProduto, int quantidade, BigDecimal precoOriginal, BigDecimal precoVenda, BigDecimal valorDesconto) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.precoOriginal = precoOriginal;
        this.precoVenda = precoVenda;
        this.valorDesconto = valorDesconto;
    }

    public long getIdPedidoItem() {
        return idPedidoItem;
    }

    public void setIdPedidoItem(long idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(BigDecimal precoOriginal) {
        this.precoOriginal = precoOriginal;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}
