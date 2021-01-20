package com.pdi.projetopdi.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.repository.UsuarioRepository;
import com.pdi.projetopdi.logic.LoginViewModel;
import com.pdi.projetopdi.logic.factory.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText campoLoginUsuario;
    private EditText campoSenhaUsuario;
    private Button botaoEntrar;
    private String loginDigitado;
    private String senhaDigitada;

    SharedPreferences.Editor editor;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        UsuarioRepository repository =UsuarioRepository.getInstance(this);
        LoginViewModelFactory factory = new LoginViewModelFactory(repository);
        this.loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);

        this.campoLoginUsuario = findViewById(R.id.usuarioLogin);
        this.campoSenhaUsuario = findViewById(R.id.usuarioSenha);
        this.botaoEntrar = findViewById(R.id.botaoAcessar);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.editor = preferences.edit();
        clicouBotao();
    }

    protected void onResume() {
        super.onResume();
        clicouBotao();
    }

    public void clicouBotao() {
        this.botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loginDigitado = campoLoginUsuario.getText().toString().trim();
                        senhaDigitada = campoSenhaUsuario.getText().toString().trim();
                        if(loginViewModel.validacaoLogin(loginDigitado, senhaDigitada)){
                            startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
                        }else{
                            Toast.makeText(getApplication(),"Login ou senha incorretos!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
