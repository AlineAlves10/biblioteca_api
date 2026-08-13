package com.example.biblioteca.repository.specification;

import com.example.biblioteca.entities.Autor;
import com.example.biblioteca.entities.Livro;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class LivroSpecification {

    public Specification<Livro> searchLivroByTitulo(String titulo) {
        return (root, query, criteria) ->
                criteria.like(
                        criteria.lower(root.get("titulo")),
                        "%" + titulo.toLowerCase() + "%"
                );
    }

    public Specification<Livro> anoIgual(Integer ano) {
        return (root, query, cb) ->
                cb.equal(root.get("anoPublicacao"), ano);
    }

    public Specification<Livro> autorContem(String nomeAutor) {
        return (root, query, cb) -> {

            Join<Livro, Autor> autor = root.join("autor");

            return cb.like(
                    cb.lower(autor.get("nome")),
                    "%" + nomeAutor.toLowerCase() + "%"
            );
        };
    }

}
