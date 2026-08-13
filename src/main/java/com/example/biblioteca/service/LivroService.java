package com.example.biblioteca.service;

import com.example.biblioteca.entities.Livro;
import com.example.biblioteca.exceptions.BadRequestException;
import com.example.biblioteca.exceptions.LivroNaoEncontradoException;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.repository.specification.LivroSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<Livro> buscarTodos(){
        return repository.findAll();
    }

    public Livro buscarPorId(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("livro nao encontrado"));
    }

    public List<Livro> buscarLivroPorTitulo(String titulo){
        Specification<Livro> spec =
                livroSpecification.searchLivroByTitulo(titulo);

        return repository.findAll(spec);
    }

    public List<Livro> buscarPorAno(Integer ano){
        Specification<Livro> spec =
                livroSpecification.anoIgual(ano);

        return repository.findAll(spec);
    }

    public List<Livro> buscarPorAutor(String nomeAutor){
        Specification<Livro> spec =
                livroSpecification.autorContem(nomeAutor);

        return repository.findAll(spec);
    }

    public List<Livro> buscarSeDisponivel() {
        return repository.livroDisponivel();
    }
}
