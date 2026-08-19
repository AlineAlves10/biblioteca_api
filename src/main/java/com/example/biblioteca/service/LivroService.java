package com.example.biblioteca.service;

import com.example.biblioteca.entities.Livro;
import com.example.biblioteca.exceptions.BadRequestException;
import com.example.biblioteca.exceptions.LivroNaoEncontradoException;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.repository.specification.LivroSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final LivroSpecification livroSpecification;

    public Livro atualizarLivro(Integer id, Livro livro) {
        int anoAtual = LocalDate.now().getYear();

        return repository.findById(id)
                .map(livroExistente -> {
                    if (livro.getAnoPublicacao() > anoAtual){
                        throw new BadRequestException("O ano do livro nao pode se maior que o ano atual");
                    }
                    livroExistente.setTitulo(livro.getTitulo());
                    livroExistente.setAnoPublicacao(livro.getAnoPublicacao());
                    return repository.save(livroExistente);
                })
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro nao encontrado"));
    }

    public void deletarLivro(Integer id){
        repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro nao encontrado"));
        repository.deleteById(id);
    }

    public void salvarLivro(Livro livro){
        repository.save(livro);
    }

    public Page<Livro> buscarTodos(Pageable pageable,
                                   String nomeAutor,
                                   Integer ano,
                                   String titulo){

        Specification<Livro> spec = null;

        if(nomeAutor!= null){
            spec = livroSpecification.autorContem(nomeAutor);
        }
        if(ano!= null){
            if(spec!= null){
                spec = spec.and(livroSpecification.anoIgual(ano));
            }
            else{
                spec = livroSpecification.anoIgual(ano);
            }
        }
        if(titulo!= null){
            if(spec!= null){
                spec = spec.and(livroSpecification.searchLivroByTitulo(titulo));
            }
            else{
                spec = livroSpecification.searchLivroByTitulo(titulo);
            }
        }


        return repository.findAll(spec, pageable);
    }

    public Livro buscarPorId(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("livro nao encontrado"));
    }

    public Page<Livro> buscarSeDisponivel(Pageable page) {
        return repository.livroDisponivel(page);
    }
}
