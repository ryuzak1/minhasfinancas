package com.minhasfinancas.service;


import com.minhasfinancas.excepiton.ErroAutenticacao;
import com.minhasfinancas.excepiton.RegraNegocioExcepiton;
import com.minhasfinancas.model.entity.Usuario;
import com.minhasfinancas.model.respository.UsuarioRepository;
import com.minhasfinancas.service.Impl.UsuarioServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @SpyBean
    UsuarioServiceImpl service;
    @MockBean
    UsuarioRepository repository;



    @Test
    public void deveAutenticarUsuarioComSucesso(){

        String email = "jose@jose";
        String senha  = "senha";
        Usuario usuario = Usuario.builder().email("jose@jose.com").senha("123").id(1l).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        Usuario result = service.autenciar(email,senha);
        Assertions.assertThat(result).isNotNull();
    }
    @Test
    public void deveLancarErroQuandoNEncontrarUsuarioCadastrado(){

        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        service.autenciar("jose2@jose.com","senha");

    }

    @Test
    public void deveSalvarUsuario(){
        Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
        Usuario usuario = Usuario.builder().email("jose@jose.com").senha("123").nome("jose").id(1l).build();

        Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
        Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
        Assertions.assertThat(usuarioSalvo).isNotNull();
        Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
        Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("jose");
        Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("jose@jose.com");
        Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("123");



    }
    @Test
    public void naoDeveSalvarUsuarioComEmailJaCadastrado(){
        String email = "jose@jose.com";
        Usuario usuario = Usuario.builder().email(email).senha("123").nome("jose").id(1l).build();
        Mockito.doThrow(RegraNegocioExcepiton.class).when(service).validarEmail(email);
        service.salvarUsuario(usuario);
        Mockito.verify(repository,Mockito.never()).save(usuario);
    }

    @Test
    public void deveLancarErroQuandoSenhaNaoBater(){
        Usuario usuario = Usuario.builder().email("jose@jose.com").senha("123").id(1l).build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

        Throwable exepiton = Assertions.catchThrowable(()->service.autenciar("jose2@jose.com","senha"));
        Assertions.assertThat(exepiton).isInstanceOf(ErroAutenticacao.class).hasMessage("erro!");


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
