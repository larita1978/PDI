package com.pdi.projetopdi.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public BigDecimal getPreco() {
        return preco;//.scaleByPowerOfTen(2).intValue();
    }

    public void setPreco(double preco) {
        this.preco = new BigDecimal(preco).setScale(2, RoundingMode.HALF_EVEN).divide(new BigDecimal(100));;
    }
}
