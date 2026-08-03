package com.example.biblioteca.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Date dataDevolucaoPrevista;
    private String status;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Usuario usuario;
}
