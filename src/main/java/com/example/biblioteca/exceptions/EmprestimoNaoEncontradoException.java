package com.example.biblioteca.exceptions;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException(String message) {
        super(message);
    }
}
