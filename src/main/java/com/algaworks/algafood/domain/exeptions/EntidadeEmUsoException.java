package com.algaworks.algafood.domain.exeptions;

public class EntidadeEmUsoException extends RuntimeException{
    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
