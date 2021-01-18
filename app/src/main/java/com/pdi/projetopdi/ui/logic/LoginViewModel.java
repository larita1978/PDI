package com.pdi.projetopdi.ui.logic;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.pdi.projetopdi.model.MD5;
import com.pdi.projetopdi.repository.UsuarioRepository;
import com.pdi.projetopdi.model.Usuario;

import java.security.NoSuchAlgorithmException;

public class LoginViewModel extends ViewModel {

    private UsuarioRepository usuarioRepository;

    private String loginDigitado;
    private String senhaDigitada;

    SharedPreferences.Editor editor;
//    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
    private boolean teste;


    public LoginViewModel(UsuarioRepository repository) {
//        super(application);
//        this.usuarioRepository = UsuarioRepository.getInstance(application.getApplicationContext());
        this.usuarioRepository = repository;
    }

    // método para verificar buscar o usuario digitado no banco e validar se é igual a null
    public boolean validacaoLogin(String loginDigitado, String senhaDigitada){
        this.loginDigitado = loginDigitado;
        this.senhaDigitada = senhaDigitada;
        validacaoDigitadoNull();

        if (teste){
            return true;
        }
        return false;
    }

        // método para verificar se foi digitado algo nos campos login e senha
    public void validacaoDigitadoNull(){
        if(!(loginDigitado.length()==0) || !(senhaDigitada.length()==0)){ //considera espaços, colocar um trim para garantir que não ha espaços em branco
            buscaUsuario();
        }

        Log.i("teste","Entrou na validação de valor igual a null e login ="
                + loginDigitado +" senha = "
                + senhaDigitada);
//        Toast.makeText(getApplication(),"Login ou senha vazios!", Toast.LENGTH_SHORT).show();

    }

    private void buscaUsuario() {
        Usuario userBanco = null;
        userBanco = usuarioRepository.buscaUsuarioPorLogin(loginDigitado);
        if(!userBanco.equals(null)){
            validarLoginDigitadoComLoginBanco(userBanco);
        }
//            Toast.makeText(getApplication(),"Login ou senha incorretos!", Toast.LENGTH_SHORT).show();
    }

    // método para validar se o login e a senha digitados é igual ao encontrado no banco
    public void validarLoginDigitadoComLoginBanco(Usuario userBanco){
        try {
//            this.editor = preferences.edit();
            if(loginDigitado.equals(userBanco.getLogin()) && new MD5(senhaDigitada).getNovaSenha().equals(userBanco.getSenha()) ) {
                int idUsuario = (int) userBanco.getIdUsuario();
//                editor.putInt("login", idUsuario );
//                editor.commit();
                this.teste= true;
            }else{
                this.teste = false;
//                Toast.makeText(getApplication(),"Login ou senha incorretos!", Toast.LENGTH_SHORT).show();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
