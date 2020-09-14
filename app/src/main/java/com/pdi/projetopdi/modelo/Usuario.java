package com.pdi.projetopdi.modelo;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class Usuario implements Serializable {

    private long idUsuario;
    private String nome;
    private String login;
    private String senha;

    public Usuario(long idUsuario, String nome, String login, String senha) throws NoSuchAlgorithmException {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = new MD5(senha).getNovaSenha();
    }

    public Usuario(){

    }

    public Usuario(String nome, String login, String senha) throws NoSuchAlgorithmException {
        this.nome = nome;
        this.login = login;
        this.senha = new MD5(senha).getNovaSenha();
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
