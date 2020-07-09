package com.pdi.projetopdi.modelo;

import java.math.BigDecimal;

public class Produto {
    private int idproduto;
    private String descricao;
    private BigDecimal preco;

    public Produto(int idproduto, String descricao, BigDecimal preco) {
        this.idproduto = idproduto;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(){

    }

    public Produto(String descricao, BigDecimal preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idusuario) {
        this.idproduto = idusuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPreco() {
        return preco.scaleByPowerOfTen(2).intValue();
    }

    public void setPreco(int preco) {
        this.preco = new BigDecimal(preco);
    }
}
