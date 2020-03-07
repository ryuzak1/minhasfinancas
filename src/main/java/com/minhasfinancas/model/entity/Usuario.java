package com.minhasfinancas.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Table(name = "usuario", schema = "financas")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "nome")
    private String nome;
    @Setter
    @Getter
    @Column(name = "email")
    private String email;
    @Setter
    @Getter
    @Column(name = "senha")
    private String senha;

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setEmail("jose@gmail.com");
        usuario.setNome("Jose");
        usuario.setSenha("123");
        System.out.println("teste");
    }


}
