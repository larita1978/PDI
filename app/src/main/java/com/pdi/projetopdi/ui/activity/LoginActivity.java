package com.pdi.projetopdi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.dao.PedidoDAO;
import com.pdi.projetopdi.dao.ProdutoDAO;
import com.pdi.projetopdi.dao.UsuarioDAO;
import com.pdi.projetopdi.modelo.MD5;
import com.pdi.projetopdi.modelo.Usuario;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class LoginActivity extends AppCompatActivity {

    private EditText campoLoginUser;
    private EditText campoSenhaUser;
    private Button botaoEntrar;
    private String loginDigitado;
    private String senhaDigitada;
    private UsuarioDAO dbUser;

    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        campoLoginUser = findViewById(R.id.usuarioLogin);
        campoSenhaUser = findViewById(R.id.usuarioSenha);
        botaoEntrar = findViewById(R.id.botaoAcessar);
        dbUser = new UsuarioDAO(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();



        verificarExecucao();
    }

    @Override
    protected void onStart() {
        super.onStart();
        clicouBotao();
    }

    protected void onResume() {
        super.onResume();
        clicouBotao();
    }

    public void verificarExecucao(){
        new  Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Acessou","Acessou o onResume");
                Usuario teste = dbUser.buscaUsuarios();
                if(teste.getLogin() == null) {
                    try {
                        inserirPrimeirosDados();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    editor.putBoolean("firstRun", false).commit();
                }else{
                    Log.i("tudo certo", "Já existe dados de user!");
                }
            }
        }).start();
    }

    public void clicouBotao(){
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacaoDigitadoisNull();
            }
        });
    }


    // método para verificar se foi digitado algo nos campos login e senha
    public void validacaoDigitadoisNull(){
        loginDigitado = campoLoginUser.getText().toString();
        senhaDigitada = campoSenhaUser.getText().toString();

        if(loginDigitado.length()==0 || campoSenhaUser.getText().length()==0){ //considera espaços, colocar um trim para garantir que não ha espaços em branco
            Log.i("teste","Entrou na validação de valor igual a null e login =" + loginDigitado +" senha = " + senhaDigitada);
            Toast.makeText(LoginActivity.this,"Login ou senha vazios!", Toast.LENGTH_SHORT).show();
        }else {
            validacaoLogin();
        }
    }

    // método para verificar buscar o usuario digitado no banco e validar se é igual a null
    public void validacaoLogin(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Usuario userBanco = null;
                userBanco = dbUser.buscaUsuarioPorLogin(loginDigitado);

                if(userBanco.equals(null)){
                    Toast.makeText(LoginActivity.this,"Login ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }else{
                    validarLoginDigitadoComLoginBanco(userBanco);
                }
            }
        });

    }
    // método para validar se o login e a senha digitados é igual ao encontrado no banco
    public void validarLoginDigitadoComLoginBanco(Usuario userBanco){
        try {
            if(loginDigitado.equals(userBanco.getLogin()) && new MD5(senhaDigitada).getNovaSenha().equals(userBanco.getSenha()) ) {
                int teste = (int) userBanco.getIdUsuario();
                editor.putInt("login", teste );
                editor.commit();
                startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
            }else{
                Toast.makeText(LoginActivity.this,"Login ou senha incorretos!", Toast.LENGTH_SHORT).show();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // método para inserir alguns dados iniciais nas tabelas na primeira execução da aplicação
    public void inserirPrimeirosDados() throws NoSuchAlgorithmException, ParseException {

        UsuarioDAO user = new UsuarioDAO(this);
        user.inserirPrimeiroUsuario();

        ProdutoDAO pr = new ProdutoDAO(this);
        pr.inserirPrimeirosDadosProduto();

        PedidoDAO ped = new PedidoDAO(this);
        ped.inserirPrimeirosDadosPedido();
    }
}
