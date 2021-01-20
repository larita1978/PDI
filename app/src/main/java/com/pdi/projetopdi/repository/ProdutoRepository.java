package com.pdi.projetopdi.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pdi.projetopdi.model.Produto;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ProdutoRepository implements Closeable {

    private static final String PRODUTO = "Produto";
    private static final String IDPRODUTO = "idProduto";
    private static final String DESCRICAO = "descricao";
    private static final String PRECO = "preco";
    private DadosHelper db;

    private static ProdutoRepository INSTANCE;

    private ProdutoRepository(Context context) {
        this.db = new DadosHelper(context);
    }

    public static ProdutoRepository getInstance(Context context ){
        if(INSTANCE == null){
            INSTANCE = new ProdutoRepository(context);
        }
        return INSTANCE;
    }
    public ProdutoRepository(DadosHelper dao) {
        this.db = dao;
    }

    public String criarTabelaProduto(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS " + PRODUTO +" ( ");
        sql.append(IDPRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(DESCRICAO + " VARCHAR(30) NOT NULL, ");
        sql.append(PRECO + " VARCHAR(10) NOT NULL);");

        return sql.toString();
    }

    public void inserirProduto(Produto produto){

        ContentValues dados = new ContentValues();
        dados.put(DESCRICAO,produto.getDescricao());
        dados.put(PRECO,produto.getPreco().scaleByPowerOfTen(2).doubleValue());

        db.getWritableDatabase().insert(PRODUTO,null,dados);
        db.close();
    }

    public ArrayList<Produto> buscaProdutos(){
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT * FROM " + PRODUTO + ";";
        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Produto prod = new Produto();
            prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
            prod.setPreco(c.getDouble(c.getColumnIndex(PRECO)));

            produtos.add(prod);
        }
        c.close();
        return produtos;
    }

    public Produto buscaProdutoPorID(Long idProduto){
        String sql = "SELECT * FROM " + PRODUTO + " WHERE " + IDPRODUTO + " = " + idProduto;
        Cursor c = db.getReadableDatabase().rawQuery(sql,null);

        Produto prod = new Produto();

        while(c.moveToNext()){
            prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
            prod.setPreco(c.getDouble(c.getColumnIndex(PRECO)));
        }
        return prod;
    }

    public ArrayList<Produto> buscaProdutoDescricao(String valor){
        ArrayList<Produto> ListaProdutos = new ArrayList<Produto>();
        String sql = "SELECT * FROM " + PRODUTO + " WHERE " + DESCRICAO + " like '%" + valor +"%';";
        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Produto prod = new Produto();
            prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
            prod.setPreco(c.getDouble(c.getColumnIndex(PRECO)));

            ListaProdutos.add(prod);
        }
        c.close();
        return ListaProdutos;
    }

    public Produto buscaProdutoDescricaoAdd(String valor){
        String sql = "SELECT * FROM " + PRODUTO + " WHERE " + DESCRICAO + " like '%" + valor +"%';";
        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        Produto prod = new Produto();

        while(c.moveToNext()){
            prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
            prod.setPreco(c.getDouble(c.getColumnIndex(PRECO)));
        }
        c.close();
        return prod;
    }

    public void inserirPrimeirosDadosProduto(){
        ArrayList<Produto> produtos;
        produtos = buscaProdutos();
        if(produtos.isEmpty()) {
            inserirProduto(new Produto("Blusa Azul", new BigDecimal("69.9")));
            inserirProduto(new Produto("Blusa Branca", new BigDecimal("29.9")));
            inserirProduto(new Produto("Blusa Amarela", new BigDecimal("69.9")));
            inserirProduto(new Produto("Calça Jeans", new BigDecimal("70.50")));
            inserirProduto(new Produto("Macacão", new BigDecimal("150.58")));
        }
    }

    @SuppressLint("Recycle")
    public void updateProduto(Produto produto){
        ContentValues data=new ContentValues();
        data.put(DESCRICAO,produto.getDescricao());
        data.put(PRECO,produto.getPreco().scaleByPowerOfTen(2).doubleValue());
        db.getReadableDatabase().update(PRODUTO, data, IDPRODUTO + "="+ produto.getIdproduto(),null);
    }

    @Override
    public void close() throws IOException {
        db.close();
    }
}
