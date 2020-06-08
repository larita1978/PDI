package com.pdi.projetopdi.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pdi.projetopdi.modelo.Usuario;

public class UsuarioDao {
    public static String getCriarTabelaUsuario(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS USUARIO (");
        sql.append(" IDUSUARIO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(" NOME VARCHAR(90) NOT NULL, ");
        sql.append(" LOGIN VARCHAR(10) NOT NULL, ");
        sql.append(" SENHA VARCHAR(200) NOT NULL);");
       return sql.toString();
    }

    public static void setInserirUsuario(SQLiteOpenHelper context,SQLiteDatabase db, Usuario usuario){
        db = context.getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("IDUSUARIO", usuario.getIdUsuario());
        dados.put("NOME",usuario.getNome());
        dados.put("LOGIN",usuario.getLogin());
        dados.put("SENHA",usuario.getSenha());

        db.insert("USUARIO",null,dados);
        db.close();
    }

    public Usuario getUsuario(String login,SQLiteDatabase db,SQLiteOpenHelper context ){
        Usuario user = new Usuario();
        String sql = "SELECT * FROM USUARIO WHERE LOGIN = ?";
        db = context.getReadableDatabase();
        Cursor c = db.rawQuery(sql, new String[]{login});

        while(c.moveToNext()){
            user.setIdUsuario(c.getLong(c.getColumnIndex("IDUSUARIO")));
            user.setNome(c.getString(c.getColumnIndex("NOME")));
            user.setLogin(c.getString(c.getColumnIndex("LOGIN")));
            user.setSenha(c.getString(c.getColumnIndex("SENHA")));
        }
        c.close();
        return user;
    }
}
