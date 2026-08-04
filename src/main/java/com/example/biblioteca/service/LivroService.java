package com.example.biblioteca.service;

import com.example.biblioteca.entities.Livro;
import com.example.biblioteca.repository.LivroRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro atualizarLivro(Integer id, Livro livro) {
        return repository.findById(id)
                .map(livroExistente -> {
                    livroExistente.setTitulo(livro.getTitulo());
                    livroExistente.setAnoPublicacao(livro.getAnoPublicacao());
                    return repository.save(livroExistente);
                })
                .orElseThrow();
    }

    public void deletarLivro(Integer id){
        repository.deleteById(id);
    }

    public void salvarLivro(Livro livro){
        repository.save(livro);
    }

    public List<Livro> buscarTodos(){
        return repository.findAll();
    }

    public Optional<Livro> buscarPorId(Integer id){
        return repository.findById(id);
    }

}
