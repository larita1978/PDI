package com.pdi.projetopdi;

import android.app.Application;

import com.pdi.projetopdi.repository.ProdutoRepository;
import com.pdi.projetopdi.repository.UsuarioRepository;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class PdiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            criaDadosPrimeiraExecucao();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void criaDadosPrimeiraExecucao() throws NoSuchAlgorithmException, ParseException {
        UsuarioRepository user = UsuarioRepository.getInstance(this);
        user.inserirPrimeiroUsuario();

        ProdutoRepository pr = ProdutoRepository.getInstance(this);
        pr.inserirPrimeirosDadosProduto();

    }
}
