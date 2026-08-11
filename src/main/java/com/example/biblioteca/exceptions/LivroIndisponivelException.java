package com.example.biblioteca.exceptions;

public class LivroIndisponivelException extends RuntimeException{

    public LivroIndisponivelException(String message) {
        super(message);
    }
}
