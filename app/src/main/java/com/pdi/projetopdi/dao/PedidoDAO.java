package com.pdi.projetopdi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pdi.projetopdi.modelo.Pedido;
import com.pdi.projetopdi.modelo.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PedidoDAO {

    private static final String PEDIDO = "pedido";
    private static final String IDPEDIDO = "idPedido";
    private static final String IDUSUARIO = "idUsuario";
    private static final String CLIENTE = "cliente";
    private static final String ENDERECO = "endereco";
    private static final String DATAPEDIDO = "dataPedido";
    private static final String TOTALITENS = "totalItens";
    private static final String TOTALPRODUTOS = "totalProdutos";
    private static final String VALORTOTAL = "valorTotal";
    private DadosHelper dao;

    public PedidoDAO(Context context) {
        this.dao = new DadosHelper(context);
    }

    public PedidoDAO(DadosHelper dao) {
        this.dao = dao;
    }

    public String getCriarTabelaPedido(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS " + PEDIDO +" ( ");
        sql.append(IDPEDIDO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(IDUSUARIO + " INTEGER NOT NULL, ");
        sql.append(CLIENTE + " VARCHAR(200) NOT NULL, ");
        sql.append(ENDERECO + " VARCHAR(200) NOT NULL, ");
        sql.append(DATAPEDIDO + " DATE NOT NULL, ");
        sql.append(TOTALITENS + " NUMERIC(18,6) NOT NULL, ");
        sql.append(TOTALPRODUTOS + " NUMERIC(18,6) NOT NULL, ");
        sql.append(VALORTOTAL + " NUMERIC(18,6) NOT NULL);");

        return sql.toString();
    }

    public void inserirPedido(Pedido pedido){

        ContentValues dados = new ContentValues();
        dados.put(IDPEDIDO, pedido.getIdPedido());
        dados.put(IDUSUARIO,pedido.getIdUsuario());
        dados.put(CLIENTE,pedido.getCliente());
        dados.put(ENDERECO,pedido.getEndereco());
        dados.put(DATAPEDIDO,pedido.getDataPedido());
        dados.put(TOTALITENS,pedido.getTotalItens().scaleByPowerOfTen(2).doubleValue());
        dados.put(TOTALPRODUTOS,pedido.getTotalProdutos().scaleByPowerOfTen(2).doubleValue());
        dados.put(VALORTOTAL,pedido.getValorTotal().scaleByPowerOfTen(2).doubleValue());
        //dados.put(PRECO,produto.getPreco().scaleByPowerOfTen(2).doubleValue());

        dao.getWritableDatabase().insert(PEDIDO,null,dados);
        dao.close();
    }

    public ArrayList<Pedido> buscaPedidos(){
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        String sql = "SELECT * FROM " + PEDIDO + ";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Pedido ped = new Pedido();
            ped.setIdPedido(c.getInt(c.getColumnIndex(IDPEDIDO)));
            ped.setIdUsuario(c.getInt(c.getColumnIndex(IDUSUARIO)));
            ped.setTotalItens(c.getDouble(c.getColumnIndex(TOTALITENS)));

            pedidos.add(ped);
        }
        c.close();
        return pedidos;
    }

        public Produto buscaPedidoPorID(int idpedido){
            String sql = "SELECT * FROM " + PEDIDO + " WHERE " + IDPEDIDO + " = " + idpedido;
            Cursor c = dao.getReadableDatabase().rawQuery(sql,null);

            Pedido ped = new Pedido();

            while(c.moveToNext()){
                prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
                prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
                prod.setPreco(c.getDouble(c.getColumnIndex(PRECO)));
            }
            return ped;
        }

        public ArrayList<Produto> buscaProdutoDescricao(String valor){
            ArrayList<Produto> produtosdesc = new ArrayList<Produto>();
            String sql = "SELECT * FROM " + PRODUTO + " WHERE " + DESCRICAO + " like '%" + valor +"%';";
            Cursor c = dao.getReadableDatabase().rawQuery(sql, null);

            while(c.moveToNext()){
                Produto prod = new Produto();
                prod.setIdproduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
                prod.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
                prod.setPreco(c.getDouble(c.getColumnIndex(PRECO)));

                produtosdesc.add(prod);
            }
            c.close();
            return produtosdesc;
        }

        public void inserirPrimeirosDados(){
            ArrayList<Produto> produtos;
            produtos = buscaProdutos();
            if(produtos.isEmpty()) {
                setInserirProduto(new Produto("Blusa Azul", new BigDecimal("69.9")));
                setInserirProduto(new Produto("Blusa Branca", new BigDecimal("29.9")));
                setInserirProduto(new Produto("Blusa Amarela", new BigDecimal("69.9")));
                setInserirProduto(new Produto("Calça Jeans", new BigDecimal("70.50")));
                setInserirProduto(new Produto("Macacão", new BigDecimal("150.58")));
            }
        }

        @SuppressLint("Recycle")
        public void updateProduto(Produto produto){
            ContentValues data=new ContentValues();
            data.put(DESCRICAO,produto.getDescricao());
            data.put(PRECO,produto.getPreco().scaleByPowerOfTen(2).doubleValue());
            dao.getReadableDatabase().update(PRODUTO, data, IDPRODUTO + "="+ produto.getIdproduto(),null);
        }


}
