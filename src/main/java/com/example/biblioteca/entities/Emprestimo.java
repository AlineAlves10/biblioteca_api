package com.example.biblioteca.entities;

import com.example.biblioteca.Enum.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataDevolucaoPrevista;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Usuario usuario;
}
