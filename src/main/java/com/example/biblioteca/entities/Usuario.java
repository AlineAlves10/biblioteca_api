package com.example.biblioteca.entities;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String email;

    @OneToMany
    private Emprestimo emprestimo;
}
