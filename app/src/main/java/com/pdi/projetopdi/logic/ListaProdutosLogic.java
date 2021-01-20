package com.pdi.projetopdi.logic;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.pdi.projetopdi.repository.ProdutoRepository;

public class ListaProdutosLogic extends AndroidViewModel {

    private ProdutoRepository pRepository;

    public ListaProdutosLogic(@NonNull Application application) {
        super(application);
    }

//    public ListaProdutosLogic(){
//        super();
//        pRepository = new pRepository.getInstance();
//    }

    public void getList(){
//        List<Produto> =
    }
}
