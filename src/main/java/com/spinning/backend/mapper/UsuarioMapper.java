package com.spinning.backend.mapper;

import org.springframework.stereotype.Component;

import com.spinning.backend.dto.UsuarioDTO;
import com.spinning.backend.model.Usuario;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setId(dto.getId());
        u.setNome(dto.getNome());
        u.setCpf(dto.getCpf());
        u.setEmail(dto.getEmail());
        u.setTelefone(dto.getTelefone());
        u.setSenha(dto.getSenha());
        u.setDataDeNascimento(dto.getDataDeNascimento());
        u.setCep(dto.getCep());
        u.setEstado(dto.getEstado());
        u.setCidade(dto.getCidade());
        u.setBairro(dto.getBairro());
        u.setLogradouro(dto.getLogradouro());
        u.setNumero(dto.getNumero());
        u.setComplemento(dto.getComplemento());
        return u;
    }

    public UsuarioDTO toDTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setCpf(u.getCpf());
        dto.setEmail(u.getEmail());
        dto.setTelefone(u.getTelefone());
        dto.setSenha(u.getSenha());
        dto.setDataDeNascimento(u.getDataDeNascimento());
        dto.setCep(u.getCep());
        dto.setEstado(u.getEstado());
        dto.setCidade(u.getCidade());
        dto.setBairro(u.getBairro());
        dto.setLogradouro(u.getLogradouro());
        dto.setNumero(u.getNumero());
        dto.setComplemento(u.getComplemento());
        return dto;
    }
}
