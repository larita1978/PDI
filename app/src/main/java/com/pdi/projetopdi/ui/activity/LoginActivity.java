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

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.logic.LoginLogic;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsuarioEditText;
    private EditText senhaUsuarioEditText;
    private Button entrarButton;
    private String loginDigitado;
    private String senhaDigitada;

    SharedPreferences.Editor editor;

    private LoginLogic loginLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.loginLogic = new LoginLogic(this);

        this.loginUsuarioEditText = findViewById(R.id.usuarioLogin);
        this.senhaUsuarioEditText = findViewById(R.id.usuarioSenha);
        this.entrarButton = findViewById(R.id.botaoAcessar);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.editor = preferences.edit();
        clicouBotao();
    }

    protected void onResume() {
        super.onResume();
        clicouBotao();
    }

    public void clicouBotao() {
        this.entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loginDigitado = loginUsuarioEditText.getText().toString().trim();
                        senhaDigitada = senhaUsuarioEditText.getText().toString().trim();
                        if(loginLogic.validacaoLogin(loginDigitado, senhaDigitada)){
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
