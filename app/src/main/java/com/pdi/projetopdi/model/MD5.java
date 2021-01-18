package com.pdi.projetopdi.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private String senha;
    private MessageDigest md5Senha;
    private String novaSenha;

    public MD5(String senha) throws NoSuchAlgorithmException {
        this.senha = senha;
        hashSenha();
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void hashSenha() throws NoSuchAlgorithmException {
        md5Senha = MessageDigest.getInstance("MD5");
        md5Senha.update(senha.getBytes(),0,senha.length());
        novaSenha = new BigInteger(1,md5Senha.digest()).toString(16);
    }
}
