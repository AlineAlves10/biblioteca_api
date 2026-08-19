package com.example.biblioteca.repository;

import com.example.biblioteca.entities.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>, JpaSpecificationExecutor<Livro> {

    @Query("""
    SELECT l
    FROM Livro l
    WHERE NOT EXISTS (
        SELECT e
        FROM Emprestimo e
        WHERE e.livro = l
          AND e.status = 'EMPRESTADO')
    """)
    Page<Livro> livroDisponivel(Pageable page);
}
