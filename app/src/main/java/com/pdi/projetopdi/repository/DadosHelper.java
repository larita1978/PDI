package com.pdi.projetopdi.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DadosHelper extends SQLiteOpenHelper {

        public DadosHelper(@Nullable Context context) {
            super(context, "DADOS", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            UsuarioRepository user = new UsuarioRepository(this);
            db.execSQL(user.criarTabelaUsuario());
            ProdutoRepository pr = new ProdutoRepository(this);
            db.execSQL(pr.criarTabelaProduto());
            PedidoRepository ped = new PedidoRepository(this);
            db.execSQL(ped.criarTabelaPedido());
            PedidoItemDAO pedItem = new PedidoItemDAO(this);
            db.execSQL(pedItem.criarTabelaPedidoItem());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
}
