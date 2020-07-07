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
            //db.execSQL(UsuarioDao.getCriarTabelaUsuario());
            //UsuarioDao.setInserirUsuario(this,db,new Usuario(0, "Larissa","lari", "teste12"));
            ProdutoDAO pr = new ProdutoDAO(this);
            db.execSQL(pr.getCriarTabelaProduto());
            //pr.inserirPrimeirosDados();  // tentar colocar em uma tread ou ver se não tem um método q nfaça isso
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
}
