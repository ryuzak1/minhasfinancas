package com.minhasfinancas.api.resource;

import com.minhasfinancas.api.dto.UsuarioDTO;
import com.minhasfinancas.excepiton.ErroAutenticacao;
import com.minhasfinancas.model.entity.Usuario;
import com.minhasfinancas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioResource {


    private final UsuarioService service;

//    public UsuarioResource(UsuarioService service) {
//        this.service = service;
//    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO usuarioDTO){
        try {
            Usuario usuario = service.autenciar(usuarioDTO.getEmail(),usuarioDTO.getSenha());
            return ResponseEntity.ok(usuario);
        }catch (ErroAutenticacao e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }


    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.builder().nome(usuarioDTO.getNome()).
                senha(usuarioDTO.getSenha()).
                email(usuarioDTO.getEmail()).
                build();
        try {
            Usuario resultado = service.salvarUsuario(usuario);
            return new ResponseEntity(resultado, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
