package com.example.biblioteca.service;

import com.example.biblioteca.entities.Emprestimo;
import com.example.biblioteca.entities.Livro;
import com.example.biblioteca.entities.Usuario;
import com.example.biblioteca.exceptions.EmprestimoNaoEncontradoException;
import com.example.biblioteca.exceptions.LivroIndisponivelException;
import com.example.biblioteca.exceptions.LivroNaoEncontradoException;
import com.example.biblioteca.exceptions.UsuarioNaoEncontradoException;
import com.example.biblioteca.repository.EmprestimoRepository;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository repository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    //salvar
    public Emprestimo salvarEmprestimo(Emprestimo emprestimo){
        return repository.save(emprestimo);
    }

    //atualizar
    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo, Integer id) {
        return repository.findById(id)
                .map(emprestimoExistente -> {

                    emprestimoExistente.setDataEmprestimo(emprestimo.getDataEmprestimo());
                    emprestimoExistente.setDataDevolucao(emprestimo.getDataDevolucao());
                    emprestimoExistente.setLivro(emprestimo.getLivro());
                    emprestimoExistente.setUsuario(emprestimo.getUsuario());

                    return repository.save(emprestimoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado."));
    }

    //deletar
    public void deletarEmprestimo(Integer id){
        repository.deleteById(id);
    }

    //ler
    public Optional<Emprestimo> verEmprestimo(Integer id){
        return repository.findById(id);
    }

    //Regra de Criar Empréstimo
    public Emprestimo realizarEmprestimo(Integer livroId, Integer usuarioId, LocalDate dataDevolucaoPrevista)  {

        //buscar livro pelo id, se nao conseguir lancar erro
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new LivroNaoEncontradoException("livro nao encontrado"));

        //Buscar o Usuario pelo ID (se não existir, lança erro).
        Usuario usu = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("usuario nao encontrato"));

        //Validar estoque: Verificar se a quantidade do livro é maior que 0. Se for 0, lança erro.
        if(livro.getQuantidade() <= 0){
            throw new LivroIndisponivelException("Livro indisponivel para emprestimo!");
        }

        //Decrementar estoque: Diminuir a quantidade do livro em 1 (livro.setQuantidade(livro.getQuantidade() - 1)).
        livro.setQuantidade(livro.getQuantidade() -1);

        //instanciar e salvar: Definir a data do empréstimo (LocalDate.now()), status (ex: EMPRESTADO) e salvar o registro.
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usu);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(dataDevolucaoPrevista);
        emprestimo.setStatus("EMPRESTADO");

        return salvarEmprestimo(emprestimo);

    }

    //regra devolucao
    public Emprestimo realizarDevolucao(Integer emprestimoId){

        Emprestimo emprestimo = repository.findById(emprestimoId)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException("emprestimo nao encontrato"));

        if("DEVOLVIDO".equals(emprestimo.getStatus())) {
            throw new RuntimeException("Este empréstimo já foi devolvido");
        }

        Livro livro = emprestimo.getLivro();
        livro.setQuantidade(livro.getQuantidade() + 1);

        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setStatus("DEVOLVIDO");

        return salvarEmprestimo(emprestimo);
    }

}
