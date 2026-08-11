package com.example.biblioteca.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException(String message) {
        super(message);
    }
}
