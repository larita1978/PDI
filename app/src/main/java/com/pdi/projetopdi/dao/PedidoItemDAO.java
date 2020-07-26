package com.pdi.projetopdi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pdi.projetopdi.modelo.Pedido;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

public class PedidoItemDAO {
        private static final String PEDIDOITEM = "pedidoitem";
        private static final String IDPEDIDOITEM = "idPedidoItem";
        private static final String IDPEDIDO = "idPedido";
        private static final String IDPRODUTO = "idProduto";
        private static final String QUANTIDADE = "quantidade";
        private static final String PRECOORIGINAL = "precoOriginal";
        private static final String PRECOVENDA = "precoVenda";
        private static final String VALORDESCONTO = "valorDesconto";
        private DadosHelper dao;

        public PedidoItemDAO(Context context) {
            this.dao = new DadosHelper(context);
        }

        public PedidoItemDAO(DadosHelper dao) {
            this.dao = dao;
        }

        public String criarTabelaPedidoItem(){
            StringBuilder sql = new StringBuilder();

            sql.append("CREATE TABLE IF NOT EXISTS " + PEDIDOITEM +" ( ");
            sql.append(IDPEDIDOITEM + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
            sql.append(IDPRODUTO + " INTEGER NOT NULL, ");
            sql.append(IDPEDIDO + " VARCHAR(200) NOT NULL, ");
            sql.append(QUANTIDADE + " NUMERIC(18,6) NOT NULL, ");
            sql.append(PRECOORIGINAL + " NUMERIC(18,6) NOT NULL, ");
            sql.append(PRECOVENDA + " NUMERIC(18,6) NOT NULL, ");
            sql.append(VALORDESCONTO + " NUMERIC(18,6) NOT NULL);");

            return sql.toString();
        }

//        public void inserirPedidoItem(PedidoItem pedido){
//
//            ContentValues dados = new ContentValues();
////        dados.put(IDPEDIDO, pedido.getIdPedido());
//            dados.put(IDUSUARIO,pedido.getIdUsuario());
//            dados.put(CLIENTE,pedido.getCliente());
//            dados.put(ENDERECO,pedido.getEndereco());
////        dados.put(DATAPEDIDO,pedido.getDataPedido());
//            dados.put(TOTALITENS,pedido.getTotalItens().scaleByPowerOfTen(2).doubleValue());
//            dados.put(TOTALPRODUTOS,pedido.getTotalProdutos().scaleByPowerOfTen(2).doubleValue());
//            dados.put(VALORTOTAL,pedido.getValorTotal().scaleByPowerOfTen(2).doubleValue());
//
//            dao.getWritableDatabase().insert(PEDIDO,null,dados);
//            dao.close();
//        }
//
//        public ArrayList<Pedido> buscaPedidos(){
//            ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
//            String sql = "SELECT * FROM " + PEDIDO + ";";
//            Cursor c = dao.getReadableDatabase().rawQuery(sql, null);
//
//            while(c.moveToNext()){
//                Pedido ped = new Pedido();
//                ped.setIdPedido(c.getInt(c.getColumnIndex(IDPEDIDO)));
//                ped.setIdUsuario(c.getInt(c.getColumnIndex(IDUSUARIO)));
//                ped.setCliente(c.getString(c.getColumnIndex(CLIENTE)));
//                ped.setEndereco(c.getString(c.getColumnIndex(ENDERECO)));
////            ped.setDataPedido(c.getString(c.getColumnIndex(DATAPEDIDO)));
//                ped.setTotalItens(c.getDouble(c.getColumnIndex(TOTALITENS)));
//                ped.setTotalProdutos(c.getDouble(c.getColumnIndex(TOTALPRODUTOS)));
//                ped.setValorTotal(c.getDouble(c.getColumnIndex(VALORTOTAL)));
//
//                pedidos.add(ped);
//            }
//            c.close();
//            return pedidos;
//        }
//
//        public Pedido buscaPedidoPorID(int idpedido){
//            String sql = "SELECT * FROM " + PEDIDO + " WHERE " + IDPEDIDO + " = " + idpedido +";";
//
//            Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
//
//            Pedido ped = new Pedido();
//
//            while(c.moveToNext()){
//                ped.setIdPedido(c.getInt(c.getColumnIndex(IDPEDIDO)));
//                ped.setIdUsuario(c.getInt(c.getColumnIndex(IDUSUARIO)));
//                ped.setCliente(c.getString(c.getColumnIndex(CLIENTE)));
//                ped.setEndereco(c.getString(c.getColumnIndex(ENDERECO)));
//                //ped.setDataPedido(c.getType(c.getColumnIndex(DATAPEDIDO)));
//                ped.setTotalItens(c.getDouble(c.getColumnIndex(TOTALITENS)));
//                ped.setTotalProdutos(c.getDouble(c.getColumnIndex(TOTALPRODUTOS)));
//                ped.setValorTotal(c.getDouble(c.getColumnIndex(VALORTOTAL)));
//            }
//            return ped;
//        }
//
//
//        public void inserirPrimeirosDados() throws ParseException {
//            ArrayList<Pedido> pedidos;
//            pedidos = buscaPedidos();
//            if(pedidos.isEmpty()) {
//                inserirPedido(new Pedido(0,"Casa da Moda", "Rua Brasilia",new BigDecimal("10"),new BigDecimal("5"),new BigDecimal("250.00")));
//                inserirPedido(new Pedido(0,"Casa da Moda", "Rua Brasilia",new BigDecimal("10"),new BigDecimal("5"),new BigDecimal("250.00")));
//
//            }
//        }

//        @SuppressLint("Recycle")
//        public void updatePedido(Pedido pedido){
//            ContentValues data = new ContentValues();
//            data.put(DESCRICAO,produto.getDescricao());
//            data.put(PRECO,produto.getPreco().scaleByPowerOfTen(2).doubleValue());
//            dao.getReadableDatabase().update(PRODUTO, data, IDPRODUTO + "="+ produto.getIdproduto(),null);
//        }


//    }

}
