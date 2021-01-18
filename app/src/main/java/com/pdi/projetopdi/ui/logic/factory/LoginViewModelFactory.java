package com.pdi.projetopdi.ui.logic.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pdi.projetopdi.repository.UsuarioRepository;
import com.pdi.projetopdi.ui.logic.LoginViewModel;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private UsuarioRepository repository;
    public LoginViewModelFactory(UsuarioRepository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(repository);
    }
}
