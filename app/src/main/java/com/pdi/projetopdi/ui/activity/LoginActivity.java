package com.pdi.projetopdi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.dao.BancoDados;
import com.pdi.projetopdi.dao.UsuarioDao;
import com.pdi.projetopdi.modelo.Usuario;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private BancoDados db;
    private EditText loginUser;
    private EditText senhaUser;
    private Button botaoEntrar;

    SharedPreferences sPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = Room.databaseBuilder(getApplicationContext(), BancoDados.class, "BancoTeste").build();

        loginUser = findViewById(R.id.usuarioLogin);
        senhaUser = findViewById(R.id.usuarioSenha);
        botaoEntrar = findViewById(R.id.botaoAcessar);

        sPreferences = getSharedPreferences("firstRun",MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta();
                Toast.makeText(LoginActivity.this, "Acessou!",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        new  Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Acessou","Acessou o onResume");
                if(sPreferences.getBoolean("firtsRun", true)) {
                    Log.i("Acessou","pós validação de 1 execução");
                    if (db.usuarioDao().getAll().isEmpty()) {
                        Log.i("Acessou","Acessou validacao tabela");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Usuario usuario = new Usuario(0, "Larissa", "lari", "teste12");
                                db.usuarioDao().insert(usuario);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Usuario Criado!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).start();
                        sPreferences.edit().putBoolean("firstRun", false).apply();
                    } else {
                        Log.i("Deu erro", "Usuario já existe");
                    }
                }else{
                    Log.i("Deu ruim", "Não deu certo criar o banco só na primeira execuçao!");
                }
            }
        }).start();
    }

    public void consulta(){
        Log.i("Acessou","Acessou metodo consulta");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("Consulta banco se user existe","nha");

                    List<Usuario> usuarios = db.usuarioDao().getAll();
                    if(usuarios.isEmpty()){
                        Log.i("Acessou","lista vazia");
                    }
                    for (Usuario usuario : usuarios){ // verificar para criar uma consulta no banco e buscar pelo user digitado, em questão de performance é melhor.
                        Log.i("Acessou","For Vazio");
                        Log.i("Dados user", "id "+ usuario.getIdUsuario() + "Login: " + usuario.getLogin() + " senha: " + usuario.getSenha());
                        if(loginUser.getText().toString().equals(usuario.getLogin()) && senhaUser.getText().toString().equals(usuario.getSenha()) ) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "Validação funcionou", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Log.i("Dados user", "Login: " + usuario.getLogin() + " senha: " + usuario.getSenha());
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "Login ou senha invalidos!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Log.i("Deu Erro lari ", "erro ao validar senha");
                        }
                    }
                    Log.i("Acessou","pós validação");
                }catch (Exception e){
                    Log.i("Deu Erro lari ", "Ocorreu um erro inesperado!");
                }
            }
        }).start();
    }
}
