package com.pdi.projetopdi.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.pdi.projetopdi.model.MD5;
import com.pdi.projetopdi.repository.UsuarioRepository;
import com.pdi.projetopdi.model.Usuario;

import java.security.NoSuchAlgorithmException;

public class LoginLogic {

    private UsuarioRepository usuarioRepository;

    private String loginDigitado;
    private String senhaDigitada;

    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    private boolean controleAcessoLogin;


    public LoginLogic(Context context) {
        this.usuarioRepository = UsuarioRepository.getInstance(context);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean validacaoLogin(String loginDigitado, String senhaDigitada){
        this.loginDigitado = loginDigitado;
        this.senhaDigitada = senhaDigitada;
        validacaoDigitadoNull();

        if (controleAcessoLogin){
            return true;
        }
        return false;
    }

    public void validacaoDigitadoNull(){
        if((loginDigitado.length()!=0) || (senhaDigitada.length()!=0)){
            buscaUsuario();
        }

    }

    private void buscaUsuario() {
        Usuario userBanco = null;
        userBanco = usuarioRepository.buscaUsuarioPorLogin(loginDigitado);
        if(!userBanco.equals(null)){
            validarLoginDigitadoComLoginBanco(userBanco);
        }
    }

    public void validarLoginDigitadoComLoginBanco(Usuario userBanco){
        try {
            this.editor = preferences.edit();
            if(loginDigitado.equals(userBanco.getLogin()) && new MD5(senhaDigitada).getNovaSenha().equals(userBanco.getSenha()) ) {
                int idUsuario = (int) userBanco.getIdUsuario();
                editor.putInt("login", idUsuario );
                editor.commit();
                this.controleAcessoLogin = true;
            }else{
                this.controleAcessoLogin = false;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
