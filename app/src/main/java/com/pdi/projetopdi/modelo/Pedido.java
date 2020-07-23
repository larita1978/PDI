package com.pdi.projetopdi.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Pedido {
    private int idPedido;
    private int idUsuario;
    private String cliente;
    private String endereco;
    private Date dataPedido;
    private BigDecimal totalItens;
    private BigDecimal totalProdutos;
    private BigDecimal valorTotal;

    public Pedido(int idPedido, int idUsuario, String cliente, String endereco, Date dataPedido, BigDecimal totalItens, BigDecimal totalProdutos, BigDecimal valorTotal) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.cliente = cliente;
        this.endereco = endereco;
        this.dataPedido = dataPedido;
        this.totalItens = totalItens;
        this.totalProdutos = totalProdutos;
        this.valorTotal = valorTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(BigDecimal totalItens) {
        this.totalItens = totalItens;
    }

    public BigDecimal getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(BigDecimal totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
