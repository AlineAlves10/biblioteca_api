package com.example.biblioteca.service;

import com.example.biblioteca.entities.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    //salvar
    public void salvarUsuario(Usuario usuario){
        repository.save(usuario);
    }

    //atualizar
    public Usuario atualizarUsuario(String email, Integer id){
        return repository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setEmail(email);
                    return repository.save(usuarioExistente);
                }).orElseThrow();
    }

    //deletar
    public void deletarUsuario(Integer id){
        repository.deleteById(id);
    }

    //ler
    public Optional<Usuario> verUsuario(Integer id){
        return repository.findById(id);
    }
}
