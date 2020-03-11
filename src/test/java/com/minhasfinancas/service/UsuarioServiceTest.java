package com.minhasfinancas.service;


import com.minhasfinancas.excepiton.RegraNegocioExcepiton;
import com.minhasfinancas.model.entity.Usuario;
import com.minhasfinancas.model.respository.UsuarioRepository;
import com.minhasfinancas.service.Impl.UsuarioServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
public class UsuarioServiceTest {


    UsuarioRepository repository;
    @MockBean
    UsuarioService service;

    @Before
    public void setup(){
        //repository= Mockito.mock(UsuarioRepository.class);
        service = new UsuarioServiceImpl(repository);

    }

    public void deveAutenticarUsuarioComSucesso(){

        String email = "jose@jose";
        String senha  = "senha";
        Usuario usuario = Usuario.builder().email("jose@jose.com").senha("123").id(1l).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        Usuario result = service.autenciar(email,senha);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void deveValidarEmail(){
        //lembrar de tirar
        //repository= Mockito.mock(UsuarioRepository.class);
        service = new UsuarioServiceImpl(repository);
        //remover


        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
        service.validarEmail("jose@jose.com");
    }
    @Test
    public void deveLancarErroValidarEmail(){
        //lembrar de tirar
        //repository= Mockito.mock(UsuarioRepository.class);
        service = new UsuarioServiceImpl(repository);
        //remover
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
        service.validarEmail("jose@jose.com");

    }


}
