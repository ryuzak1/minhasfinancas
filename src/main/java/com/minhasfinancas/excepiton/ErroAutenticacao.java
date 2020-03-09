package com.minhasfinancas.excepiton;

public class ErroAutenticacao extends RuntimeException {

    public ErroAutenticacao (String msg){
        super(msg);

    }
}
