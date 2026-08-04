package com.example.biblioteca.dtos.livroDtos;

public record LivroResponse(Integer id,
                            String titulo,
                            Integer anoPublicacao,
                            Integer quantidade,
                            String nomeAutor){
}
