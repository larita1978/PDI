package com.pdi.projetopdi.modelo;

public class Produto {
    private int idproduto;
    private String descricao;
    private double preco;

    public Produto(int idproduto, String descricao, double preco) {
        this.idproduto = idproduto;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(){

    }

    public Produto(String descricao, double preco) {
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
