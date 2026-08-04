package com.example.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String nacionalidade;

    @OneToMany(mappedBy = "autor") //serve para indicar que o relacionamento é mantido pelo campo autor lá na entidade Livro
    private List<Livro> livros;

    //TODO navegar para a lista de livros dele (autor.getLivros()).
}
