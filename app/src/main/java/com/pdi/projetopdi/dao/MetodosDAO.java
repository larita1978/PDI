package com.pdi.projetopdi.dao;

import java.util.ArrayList;

public interface MetodosDAO {
    String criarTabela();
    void inserirProduto(Object obj);
    ArrayList<Object> buscarTudo();

}
