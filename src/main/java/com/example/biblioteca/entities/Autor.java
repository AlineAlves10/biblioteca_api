package com.example.biblioteca.entities;

import jakarta.persistence.*;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String nacionalidade;

    @OneToMany
    private Livro livro;
}
