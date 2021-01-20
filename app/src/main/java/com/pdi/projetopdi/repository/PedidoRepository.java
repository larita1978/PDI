package com.pdi.projetopdi.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pdi.projetopdi.model.FormatDate;
import com.pdi.projetopdi.model.Pedido;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PedidoRepository {

    private static final String PEDIDO = "pedido";
    private static final String IDPEDIDO = "idPedido";
    private static final String IDUSUARIO = "idUsuario";
    private static final String CLIENTE = "cliente";
    private static final String ENDERECO = "endereco";
    private static final String DATAPEDIDO = "dataPedido";
    private static final String TOTALITENS = "totalItens";
    private static final String TOTALPRODUTOS = "totalProdutos";
    private static final String VALORTOTAL = "valorTotal";
    private DadosHelper db;

    private static PedidoRepository INSTANCE;

    private PedidoRepository(Context context) {
        this.db = new DadosHelper(context);
    }

    public static PedidoRepository getInstance(Context context ){
        if(INSTANCE == null){
            INSTANCE = new PedidoRepository(context);
        }
        return INSTANCE;
    }

    public PedidoRepository(DadosHelper db) {
        this.db = db;
    }


    public String criarTabelaPedido(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS " + PEDIDO +" ( ");
        sql.append(IDPEDIDO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(IDUSUARIO + " INTEGER NOT NULL, ");
        sql.append(CLIENTE + " VARCHAR(200) NOT NULL, ");
        sql.append(ENDERECO + " VARCHAR(200) NOT NULL, ");
        sql.append(DATAPEDIDO + " VARCHAR(20) NOT NULL, ");
        sql.append(TOTALITENS + " NUMERIC(18,6) NOT NULL, ");
        sql.append(TOTALPRODUTOS + " NUMERIC(18,6) NOT NULL, ");
        sql.append(VALORTOTAL + " NUMERIC(18,6) NOT NULL);");

        return sql.toString();
    }

    public int inserirPedido(Pedido pedido) throws ParseException {

        ContentValues dados = new ContentValues();

        dados.put(IDUSUARIO,pedido.getIdUsuario());
        dados.put(CLIENTE,pedido.getCliente());
        dados.put(ENDERECO,pedido.getEndereco());
        dados.put(DATAPEDIDO,String.valueOf(pedido.getDataPedido()));
        dados.put(TOTALITENS,pedido.getTotalItens().scaleByPowerOfTen(2).doubleValue());
        dados.put(TOTALPRODUTOS,pedido.getTotalProdutos().scaleByPowerOfTen(2).doubleValue());
        dados.put(VALORTOTAL,pedido.getValorTotal().scaleByPowerOfTen(2).doubleValue());

        long id = db.getWritableDatabase().insert(PEDIDO,null,dados);
        db.close();

        return (int) id;
    }

    public ArrayList<Pedido> buscaPedidos(){
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        String sql = "SELECT * FROM " + PEDIDO + ";";
        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Pedido ped = new Pedido();
            ped.setIdPedido(c.getInt(c.getColumnIndex(IDPEDIDO)));
            ped.setIdUsuario(c.getInt(c.getColumnIndex(IDUSUARIO)));
            ped.setCliente(c.getString(c.getColumnIndex(CLIENTE)));
            ped.setEndereco(c.getString(c.getColumnIndex(ENDERECO)));
            ped.setDataPedido(c.getString(c.getColumnIndex(DATAPEDIDO)));
            ped.setTotalItens(c.getDouble(c.getColumnIndex(TOTALITENS)));
            ped.setTotalProdutos(c.getDouble(c.getColumnIndex(TOTALPRODUTOS)));
            ped.setValorTotal(c.getDouble(c.getColumnIndex(VALORTOTAL)));

            pedidos.add(ped);
        }
        c.close();
        return pedidos;
    }

        public Pedido buscaPedidoPorID(int idpedido){
            String sql = "SELECT * FROM " + PEDIDO + " WHERE " + IDPEDIDO + " = " + idpedido +";";

            Cursor c = db.getReadableDatabase().rawQuery(sql,null);

            Pedido ped = new Pedido();

            while(c.moveToNext()){
                ped.setIdPedido(c.getInt(c.getColumnIndex(IDPEDIDO)));
                ped.setIdUsuario(c.getInt(c.getColumnIndex(IDUSUARIO)));
                ped.setCliente(c.getString(c.getColumnIndex(CLIENTE)));
                ped.setEndereco(c.getString(c.getColumnIndex(ENDERECO)));
                ped.setDataPedido(c.getString(c.getColumnIndex(DATAPEDIDO)));
                ped.setTotalItens(c.getDouble(c.getColumnIndex(TOTALITENS)));
                ped.setTotalProdutos(c.getDouble(c.getColumnIndex(TOTALPRODUTOS)));
                ped.setValorTotal(c.getDouble(c.getColumnIndex(VALORTOTAL)));
            }
            return ped;
        }

        @SuppressLint("Recycle")
        public void atualizaPedido(Pedido pedido) throws ParseException {
            ContentValues dados = new ContentValues();

            dados.put(IDPEDIDO, pedido.getIdPedido());
            dados.put(IDUSUARIO,pedido.getIdUsuario());
            dados.put(CLIENTE,pedido.getCliente());
            dados.put(ENDERECO,pedido.getEndereco());
            dados.put(DATAPEDIDO,String.valueOf(pedido.getDataPedido()));
            dados.put(TOTALITENS,pedido.getTotalItens().scaleByPowerOfTen(2).doubleValue());
            dados.put(TOTALPRODUTOS,pedido.getTotalProdutos().scaleByPowerOfTen(2).doubleValue());
            dados.put(VALORTOTAL,pedido.getValorTotal().scaleByPowerOfTen(2).doubleValue());

            db.getReadableDatabase().update(PEDIDO, dados, IDPEDIDO + "="+ pedido.getIdPedido(),null);
        }


}
