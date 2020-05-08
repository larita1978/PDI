package com.pdi.projetopdi.dao;

import androidx.room.RoomDatabase;

import com.pdi.projetopdi.modelo.Usuario;

@androidx.room.Database(entities ={Usuario.class}, version = 1, exportSchema = false)
public abstract class BancoDados extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();
}