package com.pdi.projetopdi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;

public class Pedido {
    private int idPedido;
    private int idUsuario;
    private String cliente;
    private String endereco;
    private String dataPedido;
    private BigDecimal totalItens;
    private BigDecimal totalProdutos;
    private BigDecimal valorTotal;

    public Pedido(){}

    public Pedido(int idPedido, int idUsuario,String cliente,String endereco,BigDecimal totalItens,
                  BigDecimal totalProdutos, BigDecimal valorTotal) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.cliente = cliente;
        this.endereco = endereco;
        this.totalItens = totalItens;
        this.totalProdutos = totalProdutos;
        this.valorTotal = valorTotal;
    }

    public Pedido(int idUsuario, String cliente, String endereco,String dataPedido,
                  BigDecimal totalItens,BigDecimal totalProdutos, BigDecimal valorTotal) {
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

    public String getDataPedido() throws ParseException {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(double totalItens) {
        this.totalItens = new BigDecimal(totalItens).setScale(2, RoundingMode.HALF_EVEN)
                .divide(new BigDecimal(100));
    }

    public BigDecimal getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(double totalProdutos) {
        this.totalProdutos = new BigDecimal(totalProdutos).setScale(2, RoundingMode.HALF_EVEN)
                .divide(new BigDecimal(100));
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = new BigDecimal(valorTotal).setScale(2, RoundingMode.HALF_EVEN)
                .divide(new BigDecimal(100));
    }
}
