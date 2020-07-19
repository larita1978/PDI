package com.pdi.projetopdi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.dao.UsuarioDAO;
import com.pdi.projetopdi.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoLoginUser;
    private EditText campoSenhaUser;
    private Button botaoEntrar;
    private String loginDigitado;
    private String senhaDigitada;
    private UsuarioDAO dbUser;

    SharedPreferences sPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        campoLoginUser = findViewById(R.id.usuarioLogin);
        campoSenhaUser = findViewById(R.id.usuarioSenha);
        botaoEntrar = findViewById(R.id.botaoAcessar);



        dbUser = new UsuarioDAO(this);

        dbUser.setInserirUsuario(new Usuario("teste","teste","12"));

        sPreferences = getSharedPreferences("firstRun",MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validacaoDigitadoisNull();
                //validacaoLogin();
                //Toast.makeText(LoginActivity.this, "Acessou!",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
            }
        });
    }


    public void validacaoDigitadoisNull(){
        loginDigitado = campoLoginUser.getText().toString();
        senhaDigitada = campoSenhaUser.getText().toString();

        if(campoLoginUser.getText().length()==0 || campoSenhaUser.getText().length()==0){
            Log.i("teste","Entrou na validação de valor igual a null e login =" + loginDigitado +" senha = " + senhaDigitada);
            Toast.makeText(LoginActivity.this,"Login ou senha vazios!", Toast.LENGTH_SHORT).show();
        }else {
            validacaoLogin();
        }
    }

    public void validacaoLogin(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Usuario userBanco = dbUser.getUsuarioPorLogin(loginDigitado);
                if(userBanco.equals(null)){
                    Log.i("Acessou","Usuario ou senha incorretos");
                }else{
                    if(loginDigitado.equals(userBanco.getLogin()) && senhaDigitada.equals(userBanco.getSenha()) ) {
                        Toast.makeText(LoginActivity.this,"Login ou senha corretos!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this,"Login ou senha incorretos!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        new  Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Acessou","Acessou o onResume");
//                if(sPreferences.getBoolean("firtsRun", true)) {
//                    Log.i("Acessou","pós validação de 1 execução");
//                    if (db.getAll().isEmpty()) {
//                        Log.i("Acessou","Acessou validacao tabela");
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Usuario usuario = new Usuario(0, "Larissa", "lari", "teste12");
//                                db.usuarioDao().insert(usuario);
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(LoginActivity.this, "Usuario Criado!", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        }).start();
//                        sPreferences.edit().putBoolean("firstRun", false).apply();
//                    } else {
//                        Log.i("Deu erro", "Usuario já existe");
//                    }
//                }else{
//                    Log.i("Deu ruim", "Não deu certo criar o banco só na primeira execuçao!");
//                }
//            }
//        }).start();
//    }

//    public void consulta(){
//        Log.i("Acessou","Acessou metodo consulta");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Log.i("Consulta banco se user existe","nha");
//
//
//                    Usuario userBanco = dbUser.getUsuarioPorLogin(loginUser.toString());
//                    if(userBanco.equals(null)){
//                        Log.i("Acessou","Usuario não encontrado");
//                    }
//                    if(loginUser.getText().toString().equals(userBanco.getLogin()) && senhaUser.getText().toString().equals(userBanco.getSenha()) ) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(LoginActivity.this, "Validaço funcionou", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
//                                }
//                            });
//                        }else{
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(LoginActivity.this, "Login ou senha invalidos!", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            Log.i("Deu Erro lari ", "erro ao validar senha");
//                        }
//
//                    Log.i("Acessou","pós validação");
//                }catch (Exception e){
//                    Log.i("Deu Erro lari ", "Ocorreu um erro inesperado!");
//                }
//            }
//        }).start();
//    }
}
