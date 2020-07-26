package com.pdi.projetopdi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pdi.projetopdi.modelo.Usuario;

public class DadosHelper extends SQLiteOpenHelper {

        public DadosHelper(@Nullable Context context) {
            super(context, "DADOS", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            UsuarioDAO user = new UsuarioDAO(this);
            db.execSQL(user.criarTabelaUsuario());
            ProdutoDAO pr = new ProdutoDAO(this);
            db.execSQL(pr.criarTabelaProduto());
            PedidoDAO ped = new PedidoDAO(this);
            db.execSQL(ped.criarTabelaPedido());
            PedidoItemDAO pedItem = new PedidoItemDAO(this);
            db.execSQL(pedItem.criarTabelaPedidoItem());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
}
