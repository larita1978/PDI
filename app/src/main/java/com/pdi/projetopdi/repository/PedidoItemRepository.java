package com.pdi.projetopdi.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pdi.projetopdi.model.PedidoItem;

import java.util.ArrayList;

public class PedidoItemRepository {
    private static final String PEDIDOITEM = "pedidoitem";
    private static final String IDPEDIDOITEM = "idPedidoItem";
    private static final String IDPEDIDO = "idPedido";
    private static final String IDPRODUTO = "idProduto";
    private static final String QUANTIDADE = "quantidade";
    private static final String PRECOORIGINAL = "precoOriginal";
    private static final String PRECOVENDA = "precoVenda";
    private static final String VALORDESCONTO = "valorDesconto";
    private DadosHelper db;

    public PedidoItemRepository(Context context) {
        this.db = new DadosHelper(context);
    }

    public PedidoItemRepository(DadosHelper dao) {
        this.db = dao;
    }

    public String criarTabelaPedidoItem() {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS " + PEDIDOITEM + " ( ");
        sql.append(IDPEDIDOITEM + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(IDPRODUTO + " INTEGER NOT NULL, ");
        sql.append(IDPEDIDO + " VARCHAR(200) NOT NULL, ");
        sql.append(QUANTIDADE + " NUMERIC(18,6) NOT NULL, ");
        sql.append(PRECOORIGINAL + " NUMERIC(18,6) NOT NULL, ");
        sql.append(PRECOVENDA + " NUMERIC(18,6) NOT NULL, ");
        sql.append(VALORDESCONTO + " NUMERIC(18,6) NOT NULL);");

        return sql.toString();
    }

    public void inserirPedidoItem(PedidoItem pedidoItem) {

        ContentValues dados = new ContentValues();
        dados.put(IDPEDIDOITEM, pedidoItem.getIdPedidoItem());
        dados.put(IDPRODUTO, pedidoItem.getIdProduto());
        dados.put(IDPEDIDO, pedidoItem.getIdPedido());
        dados.put(QUANTIDADE, pedidoItem.getQuantidade());
        dados.put(PRECOORIGINAL, pedidoItem.getPrecoOriginal().scaleByPowerOfTen(2).doubleValue());
        dados.put(PRECOVENDA, pedidoItem.getPrecoVenda().scaleByPowerOfTen(2).doubleValue());
        dados.put(VALORDESCONTO, pedidoItem.getValorDesconto().scaleByPowerOfTen(2).doubleValue());

        db.getWritableDatabase().insert(PEDIDOITEM, null, dados);
        db.close();
    }

    public ArrayList<PedidoItem> buscaPedidoItens() {
        ArrayList<PedidoItem> adapter_lista_pedidos = new ArrayList<>();
        String sql = "SELECT * FROM " + PEDIDOITEM + ";";
        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {
            PedidoItem ped = new PedidoItem();
            ped.setIdPedidoItem(c.getInt(c.getColumnIndex(IDPEDIDOITEM)));
            ped.setIdProduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            ped.setIdPedido(c.getLong(c.getColumnIndex(IDPEDIDO)));
            ped.setQuantidade(c.getInt(c.getColumnIndex(QUANTIDADE)));
            ped.setPrecoOriginal(c.getDouble(c.getColumnIndex(PRECOORIGINAL)));
            ped.setPrecoVenda(c.getDouble(c.getColumnIndex(PRECOVENDA)));
            ped.setValorDesconto(c.getDouble(c.getColumnIndex(VALORDESCONTO)));

            adapter_lista_pedidos.add(ped);
        }
        c.close();
        return adapter_lista_pedidos;
    }

    public ArrayList<PedidoItem> buscaPedidoItemPorIDPedido(int idpedido) {
        ArrayList<PedidoItem> listaItens = new ArrayList<>();
        String sql = "SELECT * FROM " + PEDIDOITEM + " WHERE " + IDPEDIDO + " = " + idpedido;

        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {
            PedidoItem ped = new PedidoItem();
            ped.setIdPedidoItem(c.getInt(c.getColumnIndex(IDPEDIDOITEM)));
            ped.setIdProduto(c.getInt(c.getColumnIndex(IDPRODUTO)));
            ped.setIdPedido(c.getLong(c.getColumnIndex(IDPEDIDO)));
            ped.setQuantidade(c.getInt(c.getColumnIndex(QUANTIDADE)));
            ped.setPrecoOriginal(c.getDouble(c.getColumnIndex(PRECOORIGINAL)));
            ped.setPrecoVenda(c.getDouble(c.getColumnIndex(PRECOVENDA)));
            ped.setValorDesconto(c.getDouble(c.getColumnIndex(VALORDESCONTO)));

            listaItens.add(ped);
        }
        return listaItens;
    }

    @SuppressLint("Recycle")
    public void atualiza(PedidoItem pedidoItem) {
        ContentValues dados = new ContentValues();
        dados.put(IDPEDIDOITEM, pedidoItem.getIdPedidoItem());
        dados.put(IDPRODUTO, pedidoItem.getIdProduto());
        dados.put(IDPEDIDO, pedidoItem.getIdPedido());
        dados.put(QUANTIDADE, pedidoItem.getQuantidade());
        dados.put(PRECOORIGINAL, pedidoItem.getPrecoOriginal().scaleByPowerOfTen(2).doubleValue());
        dados.put(PRECOVENDA, pedidoItem.getPrecoVenda().scaleByPowerOfTen(2).doubleValue());
        dados.put(VALORDESCONTO, pedidoItem.getValorDesconto().scaleByPowerOfTen(2).doubleValue());

        db.getReadableDatabase().update(PEDIDOITEM, dados,
                IDPRODUTO + "=" + pedidoItem.getIdPedidoItem(), null);
    }
}
