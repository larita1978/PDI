package com.pdi.projetopdi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pdi.projetopdi.modelo.Produto;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements Closeable {

    private static final String PRODUTO = "Produto";
    private static final String IDPRODUTO = "idProduto";
    private static final String DESCRICAO = "descricao";
    private static final String PRECO = "preco";
    private DadosHelper dao;

    public ProdutoDAO(Context context) {
        this.dao = new DadosHelper(context);
    }

    public ProdutoDAO(DadosHelper dao) {
        this.dao = dao;
    }

    public String getCriarTabelaProduto(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS " + PRODUTO +" ( ");
        sql.append(IDPRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(DESCRICAO + " VARCHAR(30) NOT NULL, ");
        sql.append(PRECO + " VARCHAR(10) NOT NULL);");

        return sql.toString();
    }

    public void setInserirProduto(Produto produto){

        ContentValues dados = new ContentValues();
        //dados.put(IDPRODUTO, produto.getIdproduto());
        dados.put(DESCRICAO,produto.getDescricao());
        dados.put(PRECO,produto.getPreco());

        dao.getWritableDatabase().insert(PRODUTO,null,dados);
        dao.close();
    }

    public ArrayList<Produto> buscaProdutos(){
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT * FROM " + PRODUTO + ";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Produto prod = new Produto();
            prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
            prod.setPreco(c.getInt(c.getColumnIndex(PRECO)));

            produtos.add(prod);
        }
        c.close();
        return produtos;
    }

    public Produto buscaProdutoPorID(Long idProduto){
        String sql = "SELECT * FROM " + PRODUTO + " WHERE " + IDPRODUTO + " = " + idProduto;
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);

        Produto prod = new Produto();

        while(c.moveToNext()){
            prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
            prod.setPreco(c.getInt(c.getColumnIndex(PRECO)));
        }
        return prod;
    }

    public ArrayList<Produto> buscaProdutoDescricao(String valor){
        ArrayList<Produto> produtosdesc = new ArrayList<Produto>();
        String sql = "SELECT * FROM " + PRODUTO + " WHERE " + DESCRICAO + " like '%" + valor +"%';";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Produto prod = new Produto();
            prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
            prod.setPreco(c.getInt(c.getColumnIndex(PRECO)));

            produtosdesc.add(prod);
        }
        c.close();
        return produtosdesc;
    }

    public void inserirPrimeirosDados(){
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        produtos = buscaProdutos();
        if(produtos.isEmpty()) {
//            setInserirProduto(new Produto("Blusa Azul", new BigDecimal("69.9")));
//            setInserirProduto(new Produto("Blusa Branca", 29.9));
//            setInserirProduto(new Produto("Blusa Amarela", 69.9));
//            setInserirProduto(new Produto("Calça Jeans", 70.50));
//            setInserirProduto(new Produto("Macacão", 150.58));
        }
    }

    @SuppressLint("Recycle")
    public void updateProduto(Produto produto){
        //ContentValues dados = new ContentValues();

//        StringBuilder sql = new StringBuilder();
//        sql.append("UPDATE " + PRODUTO);
//        sql.append(" SET " + DESCRICAO + " = " + produto.getDescricao() + " ");
//        sql.append(PRECO + " = " + produto.getPreco());
//        sql.append(" WHERE " + IDPRODUTO + " = " + produto.getIdproduto() + ";");


//        String sql = "UPDATE " + PRODUTO + " SET " + DESCRICAO + " = '" + produto.getDescricao() + "' "
//                + PRECO + " = " + produto.getPreco() +" WHERE " + IDPRODUTO + " = " + produto.getIdproduto() + ";";

        ContentValues data=new ContentValues();
        data.put(DESCRICAO,produto.getDescricao());
        data.put(PRECO,produto.getPreco());
//        data.put("Field3","male");
        dao.getReadableDatabase().update(PRODUTO, data, IDPRODUTO + "="+ produto.getIdproduto(),null);
//        dao.getDb().execSQL(sql);
    }

    @Override
    public void close() throws IOException {
        dao.close();
    }
}
