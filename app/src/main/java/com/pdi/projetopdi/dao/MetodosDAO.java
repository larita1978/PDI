package com.pdi.projetopdi.dao;

import java.util.ArrayList;

public interface MetodosDAO {
    String criarTabela();
    void inserir(Object T);
    ArrayList<Object> buscarTudo();

}
