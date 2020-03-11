package com.minhasfinancas.service;


import com.minhasfinancas.excepiton.RegraNegocioExcepiton;
import com.minhasfinancas.model.entity.Usuario;
import com.minhasfinancas.model.respository.UsuarioRepository;
import com.minhasfinancas.service.Impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    UsuarioService service;
    @Autowired

    @Test
    public void deveValidarEmail(){
        UsuarioRepository usuarioServiceMoki = Mockito.mock(UsuarioRepository.class);

        repository.deleteAll();
        service.validarEmail("jose@jose.com");
    }
    @Test
    public void deveLancarErroValidarEmail(){

        Usuario usuario = Usuario.builder().nome("Jose").email("jose@jose.com").build();
        repository.save(usuario);
        service.validarEmail("jose@jose.com");

    }


}
