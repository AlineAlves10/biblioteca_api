package com.example.biblioteca.service;

import com.example.biblioteca.entities.Emprestimo;
import com.example.biblioteca.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository repository;

    //salvar
    public void salvarEmprestimo(Emprestimo emprestimo){
        repository.save(emprestimo);
    }

    //atualizar
    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo, Integer id){
        return repository.findById(id)
                .map(nDataDevolucao -> {
                    nDataDevolucao.setDataEmprestimo(emprestimo.getDataEmprestimo());
                    return repository.save(nDataDevolucao);
                }).orElseThrow();
    }

    //deletar
    public void deletarEmprestimo(Integer id){
        repository.deleteById(id);
    }

    //ler
    public Optional<Emprestimo> verEmprestimo(Integer id){
        return repository.findById(id);
    }
}
