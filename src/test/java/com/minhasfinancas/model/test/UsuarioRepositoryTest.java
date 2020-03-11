package com.minhasfinancas.model.test;


import com.minhasfinancas.model.entity.Usuario;
import com.minhasfinancas.model.respository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Table;
import java.util.Optional;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void verificaExisteEmail() {
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        boolean resultado = repository.existsByEmail("maria@jose.com");
        if (resultado) {
            System.out.println("Usuário existente");
        }

        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    public void verificaNaoExisteEmail() {


        boolean resultado = repository.existsByEmail("maria@jose.com");

        Assertions.assertThat(resultado).isFalse();

    }

    @Test
    public void salvarUsuario() {
        Usuario usuario = criarUsuario();

        Usuario usuarioSalvo = repository.save(usuario);
        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        Usuario usuario = criarUsuario();

        entityManager.persist(usuario);

        Optional<Usuario> result = repository.findByEmail("maria@maria.com");

        Assertions.assertThat(result.isPresent()).isTrue();

    }
    @Test
    public void deveRetornarUsuarioVazioAoBuscar(){

        Optional<Usuario> result = repository.findByEmail("maria@maria.com");

        Assertions.assertThat(result.isPresent()).isFalse();

    }

    public static Usuario criarUsuario() {
        return Usuario.builder().nome("maria").email("maria@maria.com").senha("senha").build();

    }
}
