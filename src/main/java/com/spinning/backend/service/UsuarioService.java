package com.spinning.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spinning.backend.dto.UsuarioDTO;
import com.spinning.backend.mapper.UsuarioMapper;
import com.spinning.backend.model.Usuario;
import com.spinning.backend.repository.UsuarioRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    public UsuarioDTO criar(UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(usuario));
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        return mapper.toDTO(usuario);
    }

    public List<UsuarioDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario existente = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        Usuario atualizado = mapper.toEntity(dto);
        atualizado.setId(existente.getId());
        return mapper.toDTO(repository.save(atualizado));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}
