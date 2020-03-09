package com.minhasfinancas.service;

import com.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {

    Usuario autenciar(String email, String senha);
    Usuario salvarUsuario(Usuario usuario);
    void validarEmail(String email);
}
