package com.pdi.projetopdi.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pdi.projetopdi.modelo.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE nome like :nometxt limit 1")
    Usuario findByName(String nometxt);

    @Insert
    void insert(Usuario usuario);

    @Insert
    void instertAll(List<Usuario> usuarios);

    @Update
    void update (Usuario usuario);

    @Delete
    void delete(Usuario usuario);
}
