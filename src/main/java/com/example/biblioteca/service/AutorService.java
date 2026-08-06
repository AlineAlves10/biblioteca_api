package com.example.biblioteca.service;

import com.example.biblioteca.entities.Autor;
import com.example.biblioteca.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;

    //salvar
    public void salvarAutor(Autor autor){
        repository.save(autor);
    }

    //deletar
    public void deletarAutor(Integer id){
        repository.deleteById(id);
    }

    //ler
    public Optional<Autor> verAutor(Integer id){
        return repository.findById(id);
    }

    //atualizar
    public Autor atualizar(String nacionalidade, Integer id) {
        return repository.findById(id)
                .map(autorExistente -> {
                    autorExistente.setNacionalidade(nacionalidade);
                    return repository.save(autorExistente);
                })
                .orElseThrow();
    }
}
