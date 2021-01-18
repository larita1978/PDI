package com.pdi.projetopdi.repository;

import java.util.ArrayList;

public interface Repository {
    String criarTabela();
    void inserir(Object T);
    ArrayList<Object> buscarTudo();

}
