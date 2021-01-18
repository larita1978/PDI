package com.pdi.projetopdi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class PedidoItem implements Parcelable {
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

    protected PedidoItem(Parcel in) {
        idPedidoItem = in.readLong();
        idProduto = in.readLong();
        idPedido = in.readLong();
        quantidade = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idPedidoItem);
        dest.writeLong(idProduto);
        dest.writeLong(idPedido);
        dest.writeInt(quantidade);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PedidoItem> CREATOR = new Creator<PedidoItem>() {
        @Override
        public PedidoItem createFromParcel(Parcel in) {
            return new PedidoItem(in);
        }

        @Override
        public PedidoItem[] newArray(int size) {
            return new PedidoItem[size];
        }
    };

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
