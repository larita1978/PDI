package com.pdi.projetopdi.modelo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Usuario implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private long idUsuario;

    @ColumnInfo (name = "nome")
    private String nome;

    @ColumnInfo (name = "login")
    private String login;

    @ColumnInfo (name = "senha")
    private String senha;

    public Usuario(long idUsuario, String nome, String login, String senha){
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

//    @Ignore
//    public Usuario(String login, String senha) {
//        this.login = login;
//        this.senha = senha;
//    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
}
