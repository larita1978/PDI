package com.pdi.projetopdi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pdi.projetopdi.modelo.Usuario;

import java.security.NoSuchAlgorithmException;

public class UsuarioDAO {

    private static final String USUARIO = "usuario";
    private static final String IDUSUARIO = "idUsuario";
    private static final String NOME = "nome";
    private static final String LOGIN = "login";
    private static final String SENHA = "senha";
    private DadosHelper dao;

    public UsuarioDAO(Context context) {
        this.dao = new DadosHelper(context);
    }

    public UsuarioDAO(DadosHelper dao) {
        this.dao = dao;
    }

    public UsuarioDAO(){}

    public String criarTabelaUsuario(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS " + USUARIO + " (");
        sql.append(IDUSUARIO +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(NOME +" VARCHAR(80) NOT NULL, ");
        sql.append(LOGIN + " VARCHAR(80) NOT NULL, ");
        sql.append(SENHA + " VARCHAR(80) NOT NULL);");
       return sql.toString();
    }

    public void inserirUsuario(Usuario usuario) throws NoSuchAlgorithmException {
        ContentValues dados = new ContentValues();
        //dados.put(IDUSUARIO, usuario.getIdUsuario());
        dados.put(NOME, usuario.getNome());
        dados.put(LOGIN,usuario.getLogin());
        dados.put(SENHA,usuario.getSenha());

        dao.getWritableDatabase().insert(USUARIO,null,dados);
        dao.close();
    }

    public Usuario buscaUsuarioPorLogin(String login) throws NoSuchAlgorithmException {
        Usuario user = new Usuario();
        String sql = "SELECT * FROM USUARIO WHERE LOGIN = ?";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, new String[]{login});

//        c.moveToFirst();
        while(c.moveToNext()){
            user.setIdUsuario(c.getLong(c.getColumnIndex(IDUSUARIO)));
            user.setNome(c.getString(c.getColumnIndex(NOME)));
            user.setLogin(c.getString(c.getColumnIndex(LOGIN)));
            user.setSenha(c.getString(c.getColumnIndex(SENHA)));
        }
        c.close();
        return user;
    }

    public void inserirPrimeiroUsuario() throws NoSuchAlgorithmException {
        inserirUsuario(new Usuario("teste","teste", "12"));
    }
}
