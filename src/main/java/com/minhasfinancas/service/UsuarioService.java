package com.minhasfinancas.service;

import com.minhasfinancas.model.entity.Lancamento;
import com.minhasfinancas.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario autenciar(String email, String senha);
    Usuario salvarUsuario(Usuario usuario);
    void validarEmail(String email);
    Optional<Usuario> obterPorID(Long id);

}
